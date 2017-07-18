package cn.tvfan.akka.exception

import akka.actor.ActorSystem
import akka.actor.Props

object OtherExceptionApp extends App{
  
  val actorSystem = ActorSystem("OtherExceptionActorySystem")
  val actor = actorSystem.actorOf(Props[OtherExceptionParentActor], "otherExceptionParentActor")
  
  actor ! "create_child"
  Thread.sleep(2000)
  actorSystem.terminate()
}