package university

import org.scalatest.BeforeAndAfterAll
import org.scalatest.Finders
import org.scalatest.MustMatchers
import org.scalatest.WordSpecLike

import com.typesafe.config.ConfigFactory

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.testkit.EventFilter
import akka.testkit.TestKit
import cn.tvfan.akka.university.StudentDelayedActor
import cn.tvfan.akka.university.StudentProtocol.InitSignal
import cn.tvfan.akka.university.TeacherActor

class StudentDelayedTest
    extends TestKit(ActorSystem("TestUniversityMessageSystem", 
        ConfigFactory.parseString("""
          akka{ 
            loggers = ["akka.testkit.TestEventListener"]
            test{
              filter-leeway = 7s
            } 
          }""")))
    with WordSpecLike with MustMatchers with BeforeAndAfterAll {

  "A delayed student" must {
    "fire the QuoteRequest after 5 seconds when an InitSignal is sent to it" in {
      val teacherRef = system.actorOf(Props[TeacherActor], "teacherActor")
      val studentRef = system.actorOf(Props(new StudentDelayedActor(teacherRef)), "studentDelayedActor")

      EventFilter.info(pattern = "QuoteResponse*", occurrences = 3).intercept {
        studentRef ! InitSignal
      }
    }
  }

}