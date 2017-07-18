package com.akkademy.test

import org.scalatest.Matchers
import org.scalatest.FunSpecLike
import akka.actor.ActorSystem

import akka.pattern.ask
import scala.concurrent.duration._
import akka.actor.Props
import scala.concurrent.Await
import com.akkademy.ScalaPongActor
import akka.util.Timeout

class ScalaAskExamplesTest extends FunSpecLike with Matchers {
  implicit val timeout = Timeout(5 seconds)
  val system = ActorSystem()
  val pongActor = system.actorOf(Props(classOf[ScalaPongActor]))

  describe("FutureExamples") {
    import scala.concurrent.ExecutionContext.Implicits.global
    
    it("should print to console") {
      (pongActor ? "Ping").onSuccess(
        {
          case x: String => println("replied with:" + x)
        })
      Thread.sleep(100)
    }
  }

  describe("Pong actor") {
    it("should respond with Pong") {
      val future = pongActor ? "Ping" //uses the implicit timeout
      val result = Await.result(future.mapTo[String], 1 second)
      assert(result == "Pong")
    }

    it("should fail on unknown message") {
      val future = pongActor ? "unknown"
      intercept[Exception] {
        Await.result(future.mapTo[String], 1 second)
      }
    }
  }
}