package cn.tvfan.akka.university

import akka.actor.actorRef2Scala
import akka.actor.ActorSystem
import akka.actor.Props

import TeacherProtocol._

object StudentSimulatorApp extends App {
    //Initialize ActorSystem
  val actorSystem = ActorSystem("UniversityMessageSystem")

  //construct TeacherActor
  val teacherActorRef = actorSystem.actorOf(Props[TeacherActor])

  //send a message to TeacherActor
  teacherActorRef ! QuoteRequest

  //Let's wait for a couple of seconds before we shutdown the system
  Thread.sleep(2000)

  //shutdown the ActorSystem
  actorSystem.terminate()
}
