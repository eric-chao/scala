package cn.tvfan.akka.lifecycle

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.OneForOneStrategy
import akka.event.LoggingReceive
import akka.actor.SupervisorStrategy.Stop
import akka.actor.SupervisorStrategy.Restart

class LifecycleLoggingActor extends Actor with ActorLogging {

  log.info("Inside BasicLifecycleLoggingActor Constructor")
  log.info(context.self.toString())

  override def preStart() = {
    log.info("Inside the preStart method of BasicLifecycleLoggingActor")
  }

  def receive = LoggingReceive {
    case "hello" =>
      log.info("hello")
  }

  override def postStop() = {
    log.info("Inside the postStop method of BasicLifecycleLoggingActor")
  }

//  override val supervisorStrategy = OneForOneStrategy() {
//    //case _: MinorRecoverableException => Restart
//    case _: Exception => Stop
//  }
}