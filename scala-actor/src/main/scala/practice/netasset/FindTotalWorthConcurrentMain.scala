package practice.netasset

import scala.concurrent.duration.DurationInt

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import akka.pattern.gracefulStop

object FindTotalWorthConcurrentMain extends App {

  implicit val timeout = Timeout(5 seconds)

  println("Today is " + new java.util.Date)
  println("Tocker Units Closing Price($) Total Value($)")

  val system = ActorSystem("NetAssetPrintActorSystem")
  val mainPrintActor = system.actorOf(Props(new MainPrintActor).withDispatcher("akka.actor.writer-dispatcher"), "main-print-actor")
  mainPrintActor ! "start"

  Thread.sleep(5000)
  system.terminate()
//  try {
//    mainPrintActor ! "start"
//    val stopped: Future[Boolean] = gracefulStop(mainPrintActor, 10 seconds, MainPrintActor.ActorStop)
//    Await.result(stopped, 10 seconds)
//    println("testActor was stopped")
//  } catch {
//    case e: akka.pattern.AskTimeoutException => e.printStackTrace
//  } finally {
//    system.terminate()
//  }

}