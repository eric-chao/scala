package cn.tvfan.akka.exception.escalate

import akka.actor.Actor
import akka.actor.ActorLogging

class EscalateExceptionChildActor extends Actor with ActorLogging {
  
  override def preStart = {
    log.info("[child]: started")  
  }
  
  def receive: Actor.Receive = {
    case "throwSomeException" => {
      throw new Exception("[child]: I'm getting thrown for no reason.")
    }
  }
  
  override def postStop = {
    log.info("[child]: stopped")
  }
  
}