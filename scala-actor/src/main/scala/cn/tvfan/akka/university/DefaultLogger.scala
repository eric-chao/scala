package cn.tvfan.akka.university

import akka.actor.Actor
import akka.event.Logging.LogEvent
import akka.event.Logging.StdOutLogger

class DefaultLogger extends Actor with StdOutLogger {
  def receive: Actor.Receive = {
    case event: LogEvent => print(event)
  }
}