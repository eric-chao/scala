package cn.tvfan.akka.university

import StudentProtocol.InitSignal

import akka.actor.ActorSystem
import akka.actor.Props

object DriveApp extends App {

  //Initialize the ActorSystem
  val system = ActorSystem("UniversityMessageSystem")

  //construct teacher actor
  val teacherRef = system.actorOf(Props[TeacherActor], "teacherActor")

  //construct student actor - pass teacherRef as a constructor parameter
  val studentRef = system.actorOf(Props(new StudentActor(teacherRef)), "studentActor")
  val thridRef = system.actorOf(Props(new ThirdActor(teacherRef)), "thirdActor")

  //send message to student actor
  studentRef ! InitSignal
  thridRef ! InitSignal
  
  //wait for a couple of seconds before we shut down the system
  Thread.sleep(2000)

  //shut down the ActorSystem
  //shutdown() is deprecated - Use the terminate() method instead
  system.terminate()
}