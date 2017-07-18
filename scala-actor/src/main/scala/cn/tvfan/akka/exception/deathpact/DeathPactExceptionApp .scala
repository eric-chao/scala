package cn.tvfan.akka.exception.deathpact

import akka.actor.ActorSystem
import akka.actor.Props

object DeathPactExceptionApp extends App {
  val actorSystem = ActorSystem("DeathPatchExceptionSystem")
  val actor = actorSystem.actorOf(Props[DeathPactExceptionParentActor], "deathPactExceptionActor")
  
  //throws DeathPactException
  actor ! "create_child"
  //wait until stopped
  Thread.sleep(2000)
  //message goes to DeadLetters
  actor ! "someMessage"
  //terminate system
  actorSystem.terminate()
}