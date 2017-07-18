package practice.netasset

import akka.actor.Actor
import cn.tvfan.akka.dispatcher.ExecutionResult

class AggregateActor extends Actor {
  def receive = {
    case result: ExecutionResult =>
      println(s"It take total time ${result.costTime / 1000000000} seconds")
  }
}