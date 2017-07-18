package cn.tvfan.akka.watch

import RepositoryProtocol.QuoteRequest

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala

object WatcherApp extends App {
  val actorSystem = ActorSystem("WatchTestActorSystem")
  val actor = actorSystem.actorOf(Props[TeacherActorWatcher], "teacherActorWatcher")

  actor ! QuoteRequest
}