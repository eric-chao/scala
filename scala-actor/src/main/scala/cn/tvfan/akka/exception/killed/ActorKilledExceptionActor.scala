package cn.tvfan.akka.exception.killed

import akka.actor.Actor
import akka.actor.ActorLogging

/**
 * defaultStrategy
 * ACTORKILLEDEXCEPTION => STOP
 */
class ActorKilledExceptionActor extends Actor with ActorLogging {
  
  def receive: Actor.Receive = {
    case message: String => log.info(message)
  }
}