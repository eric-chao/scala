package cn.tvfan.akka.university

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

import StudentProtocol.InitSignal
import TeacherProtocol.QuoteRequest
import TeacherProtocol.QuoteResponse

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef

class StudentDelayedActor(teacherRef: ActorRef) extends Actor with ActorLogging {
  //val timeout: FiniteDuration = 2 seconds
  import context.dispatcher

  def receive: Actor.Receive = {
    case InitSignal => {
      context.system.scheduler.schedule(0 seconds, 3 seconds, teacherRef, QuoteRequest)
      //context.system.scheduler.scheduleOnce(2 seconds, teacherRef, QuoteRequest)
      //teacherRef ! QuoteRequest
    }

    case QuoteResponse(quoteString) => {
      log.info("[S]-delayed: Received QuoteResponse from Teacher")
      log.info(s"[S]-delayed: Print quoteString: $quoteString")
    }
  }

}