package cn.tvfan.akka.fsm

import org.slf4j.LoggerFactory

import CoffeeMachine.MachineData
import CoffeeMachine.MachineState
import CoffeeMachine.Open
import CoffeeMachine.PoweredOff
import CoffeeMachine.ReadyToBuy
import CoffeeProtocol.Balance
import CoffeeProtocol.BrewCoffee
import CoffeeProtocol.Cancel
import CoffeeProtocol.Deposit
import CoffeeProtocol.GetCostOfCoffee
import CoffeeProtocol.GetNumberOfCoffee
import CoffeeProtocol.MachineError
import CoffeeProtocol.SetCostOfCoffee
import CoffeeProtocol.SetNumberOfCoffee
import CoffeeProtocol.ShutDownMachine
import CoffeeProtocol.StartUpMachine
import akka.actor.FSM
import akka.actor.actorRef2Scala

class CoffeeMachine extends FSM[MachineState, MachineData] {

  private val logger = LoggerFactory.getLogger(classOf[CoffeeMachine])

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
    case Event(GetCostOfCoffee, _)        => self ! (stateData.costOfCoffee); stay()
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

}

object CoffeeMachine {

  sealed trait MachineState
  case object Open extends MachineState
  case object ReadyToBuy extends MachineState
  case object PoweredOff extends MachineState

  case class MachineData(currentTxTotal: Int, costOfCoffee: Int, coffeesLeft: Int)

}