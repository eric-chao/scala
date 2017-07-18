package cn.tvfan.akka.university

import TeacherProtocol.QuoteRequest
import TeacherProtocol.QuoteResponse
import akka.actor.ActorLogging
import akka.actor.Actor

class TeacherLogParameterActor(quotes: List[String]) extends Actor with ActorLogging {

  def receive: Actor.Receive = {
    case QuoteRequest =>
      import util.Random
      val quoteResponse = QuoteResponse(quotes(Random.nextInt(quotes.size)))

      log.info(quoteResponse.toString())
  }

  def quoteList = quotes
}