package com.akkademy

import akka.actor.Actor
import akka.actor.Status
import akka.actor.ActorSystem
import akka.actor.Props
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent.Await

class ScalaPongActor extends Actor {
  def receive: Actor.Receive = {
    case "Ping"     => sender ! "Pong"
    case "PingPong" => sender ! "PingPong"
    case _          => sender ! Status.Failure(new Exception("unknown message"))
  }
}

object ScalaPongActorMain extends App {
  import scala.concurrent.ExecutionContext.Implicits.global

  implicit val timeout = Timeout(5 seconds)

  val system = ActorSystem()
  val pongActor = system.actorOf(Props(classOf[ScalaPongActor]))

  def askPong(message: String): Future[String] = {
    (pongActor ? message).mapTo[String]
  }

  val future = askPong("Ping").flatMap(x => askPong("Ping" + x)).recover({ case e: Exception => "There was an error" })
  val result = Await.result(future.mapTo[String], 1 second)
  println(result)

  Thread.sleep(1000)
  system.terminate()
}