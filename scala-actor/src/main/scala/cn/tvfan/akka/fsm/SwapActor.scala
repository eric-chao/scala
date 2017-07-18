package cn.tvfan.akka.fsm

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

class SwapActor extends Actor {

  import context._
  def angry: Receive = {
    case "foo" => {
      println("[angry] foo")
      context.actorSelection("/user/*") ! "I am already angry?"
    }
    case "bar" => become(happy)
  }

  def happy: Receive = {
    case "bar" => sender ! "I am already happy :-)"
    case "foo" => {
      println("[happy] foo")
      become(angry)
    }
  }

  def receive = {
    case "foo" => {
      println("foo")
      become(happy)
    }

    case "bar" => become(happy)
  }

}

class MainActor extends Actor {
  val swapActor = context.actorOf(Props[SwapActor], "SwapActor")

  def receive: Actor.Receive = {
    case "start" => {
      swapActor ! "foo"
      swapActor ! "foo"
      swapActor ! "foo"
      swapActor ! "foo"
    }
    case x: String => println(x)
  }
}

class Main2Actor extends Actor {

  def receive: Actor.Receive = {
    case x: String => println("[main2] " + x)
  }
}

object SwapMain extends App {
  val system = ActorSystem("SwapActorSystem")
  val mainActor = system.actorOf(Props[MainActor], "MainActor")
  val main2Actor = system.actorOf(Props[Main2Actor], "Main2Actor")

  mainActor ! "start"
  
  Thread.sleep(2000)
  system.terminate()
}