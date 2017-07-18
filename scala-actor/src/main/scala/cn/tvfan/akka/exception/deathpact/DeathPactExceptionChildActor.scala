package cn.tvfan.akka.exception.deathpact

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.PoisonPill
import akka.actor.actorRef2Scala

class DeathPactExceptionChildActor extends Actor with ActorLogging {
  
  def receive: Actor.Receive = {
    case "stop" => {
      log.info("[child]: Actor going to stop and announce that it's terminated")
      self ! PoisonPill
    }
  }
}