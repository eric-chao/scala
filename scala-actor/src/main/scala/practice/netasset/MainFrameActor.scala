package practice.netasset

import scala.swing.MainFrame

import akka.actor.Actor
import scala.swing.Table
import scala.swing.Label
import scala.swing.Button

class MainFrameActor(valuesTable: Table, netAssetLabel: Label, dateLabel: Label, updateButton: Button) extends Actor {
  
  def receive: Actor.Receive = {
    case symbol: String =>
      val latestClosingPrice = StockPriceFinder.getLastestClosingPrice(symbol)
      val units = NetAssetStockPriceHelper.symbolsAndUnits(symbol)
      val value = units * latestClosingPrice
      println("%-7s %-5d %-16f %f".format(symbol, units, latestClosingPrice, value))
      sender ! value
      updateTable(symbol, units, latestClosingPrice, value)
      
    case netAsset: Double =>
      netAssetLabel.text = "Net Asset: " + netAsset
      dateLabel.text = "Last Updated: " + new java.util.Date()
      updateButton.enabled = true
  }

  def updateTable(symbol: String, units: Int, price: Double, value: Double) {
    for (i <- 0 until valuesTable.rowCount) {
      if (valuesTable(i, 0) == symbol) {
        valuesTable(i, 2) = price
        valuesTable(i, 3) = value
      }
    }
  }
  
}