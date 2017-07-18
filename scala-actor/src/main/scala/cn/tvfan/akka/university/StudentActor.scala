package cn.tvfan.akka.university

import TeacherProtocol.QuoteRequest
import TeacherProtocol.QuoteResponse
import StudentProtocol.InitSignal

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.actorRef2Scala

class StudentActor(teacherRef: ActorRef) extends Actor with ActorLogging {

  def receive: Actor.Receive = {
    case InitSignal => {
      teacherRef ! QuoteRequest
    }

    case QuoteResponse(quoteString) => {
      log.info("[S] Received QuoteResponse from Teacher")
      log.info(s"[S] Print quoteString: $quoteString")
    }
  }
}