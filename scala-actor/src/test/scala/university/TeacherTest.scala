package university

import org.scalatest.BeforeAndAfterAll
import org.scalatest.Finders
import org.scalatest.MustMatchers
import org.scalatest.WordSpecLike

import com.typesafe.config.ConfigFactory

import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit.TestActorRef
import akka.testkit.TestKit
import akka.testkit.TestActor
import akka.testkit.EventFilter

import cn.tvfan.akka.university.TeacherActor
import cn.tvfan.akka.university.TeacherLogActor
import cn.tvfan.akka.university.TeacherProtocol.QuoteRequest
import cn.tvfan.akka.university.TeacherLogParameterActor
import cn.tvfan.akka.university.StudentProtocol
import cn.tvfan.akka.university.StudentActor

class TeacherTest
    extends TestKit(ActorSystem("UniversityMessageSystem", ConfigFactory.parseString("""akka.loggers = ["akka.testkit.TestEventListener"]""")))
    with WordSpecLike with MustMatchers with BeforeAndAfterAll {

  //1. sends message to the Print Actor.
  "[1] A teacher" must {
    "print a quote when a QuoteRequest message is sent" in {
      val teacherRef = TestActorRef[TeacherActor]
      teacherRef ! QuoteRequest
    }
  }

  //2. sends message to the Log Actor.
  "[2] A teacher with ActorLogging" must {
    "log a quote when a QuoteRequest message is sent" in {
      val teacherRef = TestActorRef[TeacherLogActor]
      teacherRef ! QuoteRequest
    }
  }

  //3. asserts the internal State of the Log Actor
  "[3] hava a quote list of size 4" in {
    val teacherRef = TestActorRef[TeacherLogActor]
    teacherRef.underlyingActor.quoteList must have size (4)
  }

  //4. verify log messages from eventStream
  "[4] be verifiable via EventFilter in response to a QuoteRequst that is sent" in {
    val teacherRef = TestActorRef[TeacherLogActor]
    EventFilter.info(pattern = "QuoteResponse*", occurrences = 1) intercept {
      teacherRef ! QuoteRequest
    }
  }

  //5. have a quote list of the same size as the input parameter
  "[5] have a quote list of the same size as the input parameter" in {
    val quotes = List(
      "Moderation is for cowards",
      "Anything worth doing is worth overdoing",
      "The trouble is you think you have time",
      "You never gonna know if you never even try")

    val teacherRef = TestActorRef(new TeacherLogParameterActor(quotes))
    //val teacherRef = TestActorRef(Props(new TeacherLogParameterActor(quotes)))
    teacherRef.underlyingActor.quoteList must have size (4)
    EventFilter.info(pattern = "QuoteResponse*", occurrences = 1) intercept {
      teacherRef ! QuoteRequest
    }
  }

  "[6] A student" must {
    "log a QuoteResponse eventually when an InitSignal is sent to it" in {
      import StudentProtocol.InitSignal
      val teacherRef = system.actorOf(Props[TeacherActor], "teacherActor")
      val studentRef = system.actorOf(Props(new StudentActor(teacherRef)), "studentActor")
      
      EventFilter.info(pattern = "QuoteResponse*", occurrences = 1).intercept {
        studentRef ! InitSignal
      }
    }
  }

  override def afterAll() {
    super.afterAll()
    system.terminate()
  }
}