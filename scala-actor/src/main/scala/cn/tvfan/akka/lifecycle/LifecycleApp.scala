package cn.tvfan.akka.lifecycle

import akka.actor.ActorSystem
import akka.actor.Kill
import akka.actor.PoisonPill
import akka.actor.Props
import akka.actor.actorRef2Scala

object LifecycleApp extends App {

  val actorSystem = ActorSystem("LifecycleActorSystem")
  val lifecycleActor = actorSystem.actorOf(Props[LifecycleLoggingActor], "lifecycleActor")

  println(lifecycleActor.path)

  lifecycleActor ! "hello"
  lifecycleActor ! PoisonPill
  lifecycleActor ! Kill
  lifecycleActor ! "hello"
  
  Thread.sleep(2000)
  actorSystem.terminate()
}