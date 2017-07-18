package cn.tvfan.akka.exception.escalate

import akka.actor.ActorSystem
import akka.actor.Props

object EscalateExceptionApp extends App {
  
  val actorSystem = ActorSystem("EscalateExceptionActorSystem")
  val actor = actorSystem.actorOf(Props[EscalateExceptionTopLevelActor], "escalateExceptionTopLevelkActor")
  
  actor ! "create_parent"
  Thread.sleep(3000)
  actorSystem.terminate()
}