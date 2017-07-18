package cn.tvfan.akka.fsm

import org.slf4j.LoggerFactory

import CoffeeMachine1.MachineData
import CoffeeMachine1.MachineState
import CoffeeMachine1.Open
import CoffeeMachine1.PoweredOff
import CoffeeMachine1.ReadyToBuy
import CoffeeMessage.Balance
import CoffeeMessage.BrewCoffee
import CoffeeMessage.Cancel
import CoffeeMessage.Deposit
import CoffeeMessage.GetCostOfCoffee
import CoffeeMessage.GetNumberOfCoffee
import CoffeeMessage.MachineError
import CoffeeMessage.SetCostOfCoffee
import CoffeeMessage.SetNumberOfCoffee
import CoffeeMessage.ShutDownMachine
import CoffeeMessage.StartUpMachine

import akka.actor.FSM
import akka.actor.actorRef2Scala

/**
 * Created by Arun on 12/25/15.
 */

object CoffeeMachine1 {

  sealed trait MachineState
  case object Open extends MachineState
  case object ReadyToBuy extends MachineState
  case object PoweredOff extends MachineState

  case class MachineData(currentTxTotal: Int, costOfCoffee: Int, coffeesLeft: Int)

}

object CoffeeMessage {

  trait UserMessage
  trait VendorMessage

  case class Deposit(value: Int) extends UserMessage
  case class Balance(value: Int) extends UserMessage
  case object Cancel extends UserMessage
  case object BrewCoffee extends UserMessage
  case object GetCostOfCoffee extends UserMessage

  case object ShutDownMachine extends VendorMessage
  case object StartUpMachine extends VendorMessage
  case class SetNumberOfCoffee(quantity: Int) extends VendorMessage
  case class SetCostOfCoffee(price: Int) extends VendorMessage
  case object GetNumberOfCoffee extends VendorMessage

  case class MachineError(errorMsg: String)

}

class CoffeeMachine1 extends FSM[MachineState, MachineData] {

  private val logger = LoggerFactory.getLogger(classOf[CoffeeMachine1])

  startWith(Open, MachineData(currentTxTotal = 0, costOfCoffee = 5, coffeesLeft = 10))

  when(Open) {
    case Event(_, MachineData(_, _, coffeesLeft)) if (coffeesLeft <= 0) => {
      logger.warn("No more coffee")
      sender ! MachineError("No more coffee")
      goto(PoweredOff)
    }
    case Event(Deposit(value), MachineData(currentTxTotal, costOfCoffee, coffeesLeft)) if (value + currentTxTotal) >= stateData.costOfCoffee => {
      goto(ReadyToBuy) using stateData.copy(currentTxTotal = currentTxTotal + value)
    }
    //If the total deposit is less than than the price of the coffee, then stay in the current state with the current deposit amount incremented.
    case Event(Deposit(value), MachineData(currentTxTotal, costOfCoffee, coffeesLeft)) if (value + currentTxTotal) < stateData.costOfCoffee => {
      val cumulativeValue = currentTxTotal + value
      logger.debug(s"staying at open with currentTxTotal $cumulativeValue")
      stay using stateData.copy(currentTxTotal = cumulativeValue)
    }
    case Event(SetNumberOfCoffee(quantity), _) => stay using stateData.copy(coffeesLeft = quantity)
    case Event(GetNumberOfCoffee, _) =>
      sender ! (stateData.coffeesLeft); stay()
    case Event(SetCostOfCoffee(price), _) => stay using stateData.copy(costOfCoffee = price)
    case Event(GetCostOfCoffee, _)        => println(self.path); self ! (stateData.costOfCoffee); stay()
    case Event(x: Int, _)                 => println(self.path + ": " + x); stay()
  }

  //Ignoring the case when user deposits cash during `ReadyToBuy` state
  when(ReadyToBuy) {
    case Event(BrewCoffee, MachineData(currentTxTotal, costOfCoffee, coffeesLeft)) => {
      val balanceToBeDispensed = currentTxTotal - costOfCoffee
      logger.debug(s"Balance is $balanceToBeDispensed")
      if (balanceToBeDispensed > 0) {
        sender ! Balance(value = balanceToBeDispensed)
        goto(Open) using stateData.copy(currentTxTotal = 0, coffeesLeft = coffeesLeft - 1)
      } else goto(Open) using stateData.copy(currentTxTotal = 0, coffeesLeft = coffeesLeft - 1)
    }
  }

  when(PoweredOff) {
    case (Event(StartUpMachine, _)) => goto(Open)
    case _ => {
      logger.warn("Machine Powered down.  Please start machine first with StartUpMachine")
      sender ! MachineError("Machine Powered down.  Please start machine first with StartUpMachine")
      stay()
    }
  }

  whenUnhandled {
    case Event(ShutDownMachine, MachineData(currentTxTotal, costOfCoffee, coffeesLeft)) => {
      sender ! Balance(value = currentTxTotal)
      goto(PoweredOff) using stateData.copy(currentTxTotal = 0)
    }
    case Event(Cancel, MachineData(currentTxTotal, _, _)) => {
      logger.debug(s"Balance is $currentTxTotal")
      sender ! Balance(value = currentTxTotal)
      goto(Open) using stateData.copy(currentTxTotal = 0)
    }
  }

  onTransition {
    case Open -> ReadyToBuy => logger.debug("From Transacting to ReadyToBuy")
    case ReadyToBuy -> Open => logger.debug("From ReadyToBuy to Open")
  }

  // Initialize the coffee machine
  initialize()
}
