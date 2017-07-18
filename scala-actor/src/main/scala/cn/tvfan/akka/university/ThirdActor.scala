package cn.tvfan.akka.university

import TeacherProtocol.QuoteRequest
import TeacherProtocol.QuoteResponse
import StudentProtocol.InitSignal

import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.ActorRef

class ThirdActor(teacherRef: ActorRef) extends Actor with ActorLogging {
  
  def receive: Actor.Receive = {
    case InitSignal => {
      teacherRef ! QuoteRequest
    }

    case QuoteResponse(quoteString) => {
      log.info("[T] Received QuoteResponse from Teacher")
      log.info(s"[T] Print quoteString: $quoteString")
    }
  }
}