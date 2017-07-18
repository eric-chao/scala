package cn.tvfan.akka.university

import TeacherProtocol.QuoteRequest
import TeacherProtocol.QuoteResponse

import akka.actor.ActorLogging
import akka.actor.Actor

class TeacherActor extends Actor with ActorLogging {

  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")

  def receive: Actor.Receive = {
    case QuoteRequest =>
      import util.Random
      val quoteResponse = QuoteResponse(quotes(Random.nextInt(quotes.size)))
      //println(quoteResponse)
      sender ! quoteResponse 
  }

}