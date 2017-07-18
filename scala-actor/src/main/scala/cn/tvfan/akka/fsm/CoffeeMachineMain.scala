package cn.tvfan.akka.fsm

import CoffeeProtocol.GetCostOfCoffee
import CoffeeProtocol.SetCostOfCoffee
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala

object CoffeeMachineMain extends App {

  val actorSystem = ActorSystem("coffeeActorSystem")
  val coffeeMachine = actorSystem.actorOf(Props[CoffeeMachine], "coffeeMachineActor")

  coffeeMachine ! SetCostOfCoffee(7)
  coffeeMachine ! GetCostOfCoffee
  
  Thread.sleep(2000)
  
  //shut down actor-system
  actorSystem.terminate()
}