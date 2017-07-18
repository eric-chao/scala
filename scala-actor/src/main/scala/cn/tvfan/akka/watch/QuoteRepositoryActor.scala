package cn.tvfan.akka.watch

import scala.util.Random
import RepositoryProtocol.QuoteRequest

import RepositoryProtocol.QuoteRepositoryRequest
import RepositoryProtocol.QuoteRepositoryResponse
import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.PoisonPill
import akka.actor.actorRef2Scala

class QuoteRepositoryActor extends Actor with ActorLogging {

  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")

  var repoRequestCount: Int = 1

  def receive = {
    case QuoteRepositoryRequest => {

      if (repoRequestCount > 3) {
        self ! PoisonPill
      } else {
        //Get a random Quote from the list and construct a response
        val quoteResponse = QuoteRepositoryResponse(quotes(Random.nextInt(quotes.size)))

        log.info(s"QuoteRequest received in QuoteRepositoryActor. Sending response to Teacher Actor $quoteResponse")
        repoRequestCount = repoRequestCount + 1
        sender ! QuoteRequest
      }

    }

  }

}