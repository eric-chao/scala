package cn.tvfan.akka.actor

import akka.actor.Actor

class HelloActor extends Actor {
  def receive: Actor.Receive = {
    case "hello" => println("hello back to you.")
    case HelloActor.ActorStop =>
      context.stop(self)
    case _ => println("huh?")
  }
}

object HelloActor {
  case object ActorStop
}