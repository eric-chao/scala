package cn.tvfan.akka.exception.escalate

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy.Stop
import akka.actor.Props


class EscalateExceptionTopLevelActor extends Actor with ActorLogging {
  override def supervisorStrategy = OneForOneStrategy() {
    case _: Exception => {
      log.info("[Top]: the exception from child is now handled by TopLevel Actor")
      log.info("[Top]: stopping parent actor and its children")
      Stop
    }
  }

  def receive: Actor.Receive = {
    case "create_parent" => {
      log.info("[Top]: create parent")
      val parent = context.actorOf(Props[EscalateExceptionParentActor], "sscalateExceptionParentActor")
      //Sending message to next level
      parent ! "create_child"
    }
  }
}