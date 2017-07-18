package cn.tvfan.akka.fsm

import org.scalatest.FunSpecLike
import org.scalatest.MustMatchers

import CoffeeProtocol.GetCostOfCoffee
import CoffeeProtocol.SetCostOfCoffee
import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit.ImplicitSender
import akka.testkit.TestActorRef
import akka.testkit.TestKit

class CoffeeSpec extends TestKit(ActorSystem("coffee-system")) with MustMatchers with FunSpecLike with ImplicitSender {

  describe("The Coffee Machine") {

    it("should allow setting and getting of price of coffee") {
      val coffeeMachine = TestActorRef(Props[CoffeeMachine])
      coffeeMachine ! SetCostOfCoffee(7)
      coffeeMachine ! GetCostOfCoffee
      expectMsg(7)
    }
  }
}