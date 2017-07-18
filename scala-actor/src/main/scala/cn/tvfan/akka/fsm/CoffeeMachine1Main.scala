package cn.tvfan.akka.fsm

import CoffeeMachine1.Open
import CoffeeMessage.Deposit
import CoffeeMessage.GetCostOfCoffee
import CoffeeMessage.SetCostOfCoffee

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.actor.ActorRef

object CoffeeMachine1Main extends App {

  val actorSystem = ActorSystem("coffee1ActorSystem")
  //val coffeeMachine = actorSystem.actorOf(Props[CoffeeMachine1], "coffeeMachine1Actor")

  val machines = for (i <- 1 to 5) yield actorSystem.actorOf(Props[CoffeeMachine1], "coffee-machine-" + i)

  for (i <- 0 until 5) {
    machines(i) ! SetCostOfCoffee(i+1)
  }
  
  machines.foreach(_ ! GetCostOfCoffee)
  //Thread.sleep(2000)

  //shut down actor-system
  //actorSystem.terminate()

}