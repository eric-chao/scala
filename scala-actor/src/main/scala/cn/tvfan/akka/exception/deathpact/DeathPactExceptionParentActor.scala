package cn.tvfan.akka.exception.deathpact

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.Props
import akka.actor.Terminated
import akka.actor.actorRef2Scala

/**
 * defaultStrategy
 * DEATHPACTEXCEPTION => STOP
 */
class DeathPactExceptionParentActor extends Actor with ActorLogging {

  def receive: Actor.Receive = {
    case "create_child" => {
      log.info("[parent]: creating child")
      val child = context.actorOf(Props[DeathPactExceptionChildActor], "deatchPactExceptionChildActor")
      context.watch(child)
      child ! "stop"
    }
    case "someMessage" => log.info("[parent]: some message")
    //doesn't handle terminated message
    case Terminated(x) => log.info("[parent]: " + x.toString)
  }
}