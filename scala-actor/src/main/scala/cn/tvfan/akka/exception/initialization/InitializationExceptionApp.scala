package cn.tvfan.akka.exception.initialization

import akka.actor.ActorSystem
import akka.actor.Props

object InitializationExceptionApp extends App {
  
  val actorSystem = ActorSystem("InitializationExceptionActorSystem")
  val actor = actorSystem.actorOf(Props[InitializationExceptionActor], "initializationExceptionActor")

  actor ! "someMessageWillGoToDeadLetter"
  
  actorSystem.terminate()
}