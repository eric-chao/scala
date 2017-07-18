package cn.tvfan.akka.exception.initialization

import akka.actor.Actor
import akka.actor.ActorLogging

/**
 * defaultStrategy
 * ACTORINITIALIZATIONEXCEPTION => STOP
 */
class InitializationExceptionActor extends Actor with ActorLogging {
  
  override def preStart = {
    throw new Exception("Initialization Exception")
  }
  
  def receive: Actor.Receive = {
    case _ =>
  }
}