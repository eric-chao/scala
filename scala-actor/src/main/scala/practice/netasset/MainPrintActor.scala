package practice.netasset

import scala.annotation.migration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.util.Random
import scala.util.Failure
import scala.util.Success

import MainProtocol.InitSignal
import akka.actor.Actor
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.pattern.ask
import akka.pattern.pipe
import akka.util.Timeout
import cn.tvfan.akka.dispatcher.ExecutionResult

class MainPrintActor extends Actor {

  implicit val timeout = Timeout(10 seconds)
  //val aggregateActor = context.actorOf(Props[AggregateActor], "aggregate")

  val symbolsAndUnits = StockPriceFinder.getTikersAndUnits
  val size = symbolsAndUnits.size

  val machines = for (i <- 1 to 6) yield context.actorOf(Props(new MainPrintChildActor()), "machine-" + i)
  //val results = Map[String, Future[Int]]()

  def receive: Actor.Receive = {
    case "start" => {
      val startTime = System.currentTimeMillis()
      var results = List[Future[Long]]()
      symbolsAndUnits.keys.foreach {
        symbol =>
          {
            results = (machines(Random.nextInt(6)) ? InitSignal(symbol) mapTo manifest[Long]) :: results
          }
      }
      val aggregate = Future.sequence(results).map(results => results.sum)
      //aggregate pipeTo aggregateActor
      aggregate onComplete {
        case Success(_) =>
          val endTime = System.currentTimeMillis()
          val costTime = endTime - startTime
          println(s"It take total time ${costTime} milli seconds")
          println(s"It take total time ${costTime / 1000} seconds")
          println(s"It take total time ${costTime / (1000 * 60)} minutes")
          
        case Failure(_) => println("It failed.")
      }
    }
    
    case MainPrintActor.ActorStop => context.stop(self)

  }
}

object MainPrintActor {
      
  case object ActorStop
  
}