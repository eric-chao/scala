package cn.tvfan.akka.dispatcher

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.util.Failure
import scala.util.Success

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.pattern.ask
import akka.util.Timeout

trait Message
case class InsertCommand(recordCount: Int) extends Message
case class ControlCommand(message: String, startTime: Long) extends Message
case class StartCommand(actorCount: Int) extends Message
case class ExecutionResult(costTime: Long) extends Message

class WriterActor extends Actor {
  override def preStart(): Unit = {
    println(Thread.currentThread().getName)
  }

  def receive = {
    case ic: InsertCommand => println(ic.recordCount)
    case _ => println("huh?")
  }
}

class ControlActor extends Actor {
  implicit val timeout = Timeout(5 minutes)

  def receive = {
    case msg: StartCommand =>
      val startTime = System.currentTimeMillis()
      val actors = createActors(msg.actorCount)
      val results = actors.map(actor => actor ? InsertCommand(100) mapTo manifest[Long])
      val aggregate = Future.sequence(results).map(x => ExecutionResult(x.sum))

      aggregate onComplete {
        case Success(_) =>
          val endTime = System.currentTimeMillis()
          val costTime = endTime - startTime
          println(s"It take total time ${costTime} milli seconds")
        case Failure(ex) => println("It failed: " + ex)
      }
  }

  def createActors(count: Int): List[ActorRef] = {
    val props = Props(classOf[WriterActor]).withDispatcher("akka.actor.writer-dispatcher")
    (1 to count).map(i => {
      context.actorOf(props, s"writer_$i")
    }).toList
  }
}

object Master {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("DataInitializer")
    //implicit val executionContext = system.dispatchers.lookup("writer-dispatcher")
    val actor = system.actorOf(Props[ControlActor], "controlActor")

    actor ! StartCommand(4)

    Thread.sleep(2000)
    system.terminate()
  }
}