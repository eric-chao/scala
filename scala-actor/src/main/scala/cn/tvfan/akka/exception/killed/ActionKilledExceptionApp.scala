package cn.tvfan.akka.exception.killed

import akka.actor.ActorSystem
import akka.actor.Kill
import akka.actor.Props
import akka.actor.actorRef2Scala

object ActionKilledExceptionApp extends App {
  
  val actorSystem = ActorSystem("ActorKilledExceptionActorSystem")
  val actor = actorSystem.actorOf(Props[ActorKilledExceptionActor], "actorKilledExceptionActor")
  
  actor ! "something"
  actor ! Kill
  actor ! "something else that fails into dead letter queue"
  
  actorSystem.terminate()
}