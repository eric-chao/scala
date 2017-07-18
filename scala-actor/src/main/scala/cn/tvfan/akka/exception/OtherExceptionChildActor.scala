package cn.tvfan.akka.exception

import akka.actor.Actor
import akka.actor.ActorLogging

class OtherExceptionChildActor extends Actor with ActorLogging {
  
  override def preStart = {
    log.info("[child]: starting child")
  }
  
  def receive: Actor.Receive = {
    case "throwSomeException" => {
      throw new Exception("[child]: I'm getting thrown for no reason")
    }
    
    case "someMessage" => log.info("[child]: Restarted and printing some message")
  }
  
  override def postStop = {
    log.info("[child]: stopping child")
  }
}