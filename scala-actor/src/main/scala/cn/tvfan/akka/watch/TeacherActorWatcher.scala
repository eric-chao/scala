package cn.tvfan.akka.watch

import RepositoryProtocol.QuoteRepositoryRequest
import RepositoryProtocol.QuoteRepositoryResponse
import RepositoryProtocol.QuoteRequest
import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.Props
import akka.actor.Terminated
import akka.actor.actorRef2Scala

class TeacherActorWatcher extends Actor with ActorLogging {

  val quoteRepositoryActor = context.actorOf(Props[QuoteRepositoryActor], "quoteRepositoryActor")
  context.watch(quoteRepositoryActor)

  def receive = {
    case QuoteRequest => {
      quoteRepositoryActor ! QuoteRepositoryRequest
    }
    case QuoteRepositoryResponse(s: String) => {

    }
    case Terminated(terminatedActorRef) => {
      log.error(s"Child Actor {$terminatedActorRef} Terminated")
    }
  }
}