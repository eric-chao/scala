package com.akkademy

import scala.collection.mutable.HashMap

import com.akkademy.Messages.SetRequest
import com.akkademy.Messages.GetRequest
import com.akkademy.Messages.KeyNotFoundException

import akka.actor.Actor
import akka.event.Logging
import akka.actor.Status
import akka.actor.ActorSystem
import akka.actor.Props

class AkkademyDb extends Actor {

  val map = new HashMap[String, Object]
  val log = Logging(context.system, this)

  def receive: Actor.Receive = {
    case SetRequest(key, value) => {
      log.info("Received SetRequest - key {} value: {}", key, value)
      map.put(key, value)

      sender() ! Status.Success
    }

    case GetRequest(key) => {
      log.info("Received GetRequest - key {}", key)
      val response = map.get(key)
      response match {
        case Some(x) => sender() ! x
        case None    => sender() ! Status.Failure(new KeyNotFoundException(key))
      }
    }

    case o => log.info("Received unknown message: {}", o)
  }
}

object AkkademyDbMain extends App {
  val system = ActorSystem("akkademy")
  system.actorOf(Props[AkkademyDb], name = "akkademy-db")
}
