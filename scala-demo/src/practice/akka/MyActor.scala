package practice.akka

import akka.actor.Actor
import akka.event.Logging
import akka.actor.Props

class MyActor extends Actor {
  val log = Logging(context.system, this)
  
  def receive = {
    case "test" => log.info("received test")
    case  _ => log.info("received unknown message")
  }
}

object MainTest extends App {
  //val system = ActorSystem("MySystem")
  //val myActor = system.actorOf(Props[MyActor], name = "myactor")
}