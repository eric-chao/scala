package practice.netasset

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props
import scala.swing.Button
import scala.swing.Label
import scala.swing.Table
import scala.util.Random

class NetAssetActor(valuesTable: Table, netAssetLabel: Label, dateLabel: Label, updateButton: Button) extends Actor {
  private val processors = Runtime.getRuntime.availableProcessors()
  val symbolsAndUnits = StockPriceFinder.getTikersAndUnits
  val unitSize = symbolsAndUnits.size
  val actors = for (i <- 1 to processors) yield context.actorOf(Props(new MainFrameActor(valuesTable, netAssetLabel, dateLabel, updateButton)), "mainFrameActor" + i)

  var networth = 0.0
  var times = 1
  def receive: Actor.Receive = {
    case "start" => {
      networth = 0.0; times = 1
      symbolsAndUnits.keys.foreach {
        symbol => actors(Random.nextInt(processors)) ! symbol
      }
    }

    case value: Double => {
      networth += value
      if (times == unitSize) {
        actors(Random.nextInt(processors)) ! networth
      }

      times += 1
    }

  }
}
