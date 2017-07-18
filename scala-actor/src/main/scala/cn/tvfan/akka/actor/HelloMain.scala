package cn.tvfan.akka.actor

import scala.concurrent.Await
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.pattern.gracefulStop

object HelloMain extends App {
  
  // actor need an ActorSystem
  val system = ActorSystem("HelloSystem")
  // create and start the actor
  //println(system.dispatchers.settings)
  val helloActor = system.actorOf(Props[HelloActor], name = "helloActor")

  for (i <- 1 to 10) {
    // send two messages
    helloActor ! "hello"
    helloActor ! "hello"
    helloActor ! "hello"
    helloActor ! "what"
    helloActor ! "what"
    helloActor ! "what"
  }

  // try to stop the actor graceful
  // shutdown the actor system
  try {
    val stopped: Future[Boolean] = gracefulStop(helloActor, 3 seconds, HelloActor.ActorStop)
    Await.result(stopped, 3 seconds)
    println("testActor was stopped")
  } catch {
    case e: akka.pattern.AskTimeoutException => e.printStackTrace
  } finally {
    system.terminate()
  }
}