package cn.tvfan.akka.actor

import scala.concurrent.duration.DurationInt

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.pattern.ask
import akka.util.Timeout

object Hello4 extends App {

  import Greeter._

  val system = ActorSystem("actor-demo-scala")
  val bob = system.actorOf(props("Bob", "Howya doing"))
  val alice = system.actorOf(props("Alice", "Happy to meet you"))
  bob ! Greet(alice)
  alice ! Greet(bob)
  Thread sleep 1000
  system terminate

  object Greeter {
    case class Greet(peer: ActorRef)
    case object AskName
    def props(name: String, greeting: String) = Props(new Greeter(name, greeting))
  }

  class Greeter(myName: String, greeting: String) extends Actor {
    import system.dispatcher
    implicit val timeout = Timeout(5 seconds)
    def receive = {
      case Greet(peer) => {
        val futureName = peer ? AskName
        futureName.foreach {
          name => println(s"$greeting, $name")
        }
      }
      case AskName => sender ! myName
    }
  }
}