package cn.tvfan.akka.exception.escalate

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy.Escalate
import akka.actor.Props

class EscalateExceptionParentActor extends Actor with ActorLogging {
  
  override def preStart = {
    log.info("[parent]: started")
  }
  
  override val supervisorStrategy = OneForOneStrategy() {
    case _: Exception => {
      log.info("[parent]: this exception is ducked by Parent Actor.")
      log.info("[parent]: escalate to TopLevel actor.")
      Escalate
    }
  }
  
  def receive: Actor.Receive = {
    case "create_child" => {
      log.info("[parent]: create child")
      val child = context.actorOf(Props[EscalateExceptionChildActor], "escalateExceptionChildActor")
      child ! "throwSomeException"
    }
  }
  
  override def postStop = {
    log.info("[parent]: stopped")
  }
}