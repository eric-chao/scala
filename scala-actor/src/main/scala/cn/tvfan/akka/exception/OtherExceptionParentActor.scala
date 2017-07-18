package cn.tvfan.akka.exception

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy.Restart
import akka.actor.SupervisorStrategy.Resume

/**
 * defaultStrategy
 * EXCEPTION => RESTART
 *
 * override def supervisorStrategy = OneForOneStrategy() {
 * 	 case _ : Exception => Restart
 * }
 */
class OtherExceptionParentActor extends Actor with ActorLogging {

  override def supervisorStrategy = OneForOneStrategy() {
    case _: Exception => Resume
  }
  
  def receive: Actor.Receive = {
    case "create_child" => {
      log.info("[parent]: create child")
      val child = context.actorOf(Props[OtherExceptionChildActor], "otherExceptionChildActor")
      //send message to child
      child ! "throwSomeException"
      child ! "someMessage"
    }
  }
}