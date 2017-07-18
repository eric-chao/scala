package practice.netasset

import java.awt.Color

import scala.swing.BoxPanel
import scala.swing.Button
import scala.swing.FlowPanel
import scala.swing.Label
import scala.swing.MainFrame
import scala.swing.Orientation
import scala.swing.ScrollPane
import scala.swing.SimpleSwingApplication
import scala.swing.Table
import akka.actor.Actor
//
//object NetAssetAppGUI extends SimpleSwingApplication {
//  def top = mainFrame
//
//  val mainFrame = new MainFrame {
//    title = "Net Asset"
//
//    val dateLabel = new Label {
//      text = "Last Updated:  -----"
//    }
//
//    val initialValues = NetAssetStockPriceHelper.getInitialTableValues
//    val tableTitles = Array("Ticker", "Units", "Price", "Values")
//    val valuesTable = new Table(initialValues, tableTitles) {
//      showGrid = true
//      gridColor = Color.BLACK
//    }
//
//    val updateButton = new Button { text = "Update" }
//    val netAssetLabel = new Label { text = "Net Asset: ????" }
//
//    contents = new BoxPanel(Orientation.Vertical) {
//      contents += dateLabel
//      contents += valuesTable
//      contents += new ScrollPane(valuesTable)
//
//      contents += new FlowPanel {
//        contents += updateButton
//        contents += netAssetLabel
//      }
//    }
//
//    listenTo(updateButton)
//
//    val uiUpdater = new Actor {
//      override def receive() = {
//        case (symbol: String, units: Int, price: Double, value: Double) =>
//          updateTable(symbol, units, price, value)
//        case netAsset =>
//          netAssetLabel.text = "Net Asset: " + netAsset
//          dateLabel.text = "Last Updated: " + new java.util.Date()
//          updateButton.enabled = true
//      }
//
//      //override protected def scheduler() = new SingleThreadedScheduler()
//    }
//
//    //uiUpdater.
//
//    def updateTable(symbol: String, units: Int, price: Double, value: Double) {
//      for (i <- 0 until valuesTable.rowCount) {
//        if (valuesTable(i, 0) == symbol) {
//          valuesTable(i, 2) = price
//          valuesTable(i, 3) = value
//        }
//      }
//    }
//
//  }
//
//}