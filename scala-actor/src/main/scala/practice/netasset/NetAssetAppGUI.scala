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
import akka.actor.ActorSystem
import akka.actor.Props
import scala.swing.event.ButtonClicked

object NetAssetAppGUI extends SimpleSwingApplication {

  def top = mainFrame

  val mainFrame = new MainFrame {
    title = "Net Asset"

    val dateLabel = new Label {
      text = "Last Updated:  -----"
    }

    val initialValues = NetAssetStockPriceHelper.getInitialTableValues
    val tableTitles = Array("Ticker", "Units", "Price", "Values")
    val valuesTable = new Table(initialValues, tableTitles) {
      showGrid = true
      gridColor = Color.BLACK
    }

    val updateButton = new Button { text = "Update" }
    val cleanButton = new Button { text = "Clean" }

    val netAssetLabel = new Label { text = "Net Asset: ????" }

    contents = new BoxPanel(Orientation.Vertical) {
      contents += dateLabel
      contents += valuesTable
      contents += new ScrollPane(valuesTable)

      contents += new FlowPanel {
        contents += updateButton
        contents += cleanButton
        contents += netAssetLabel
      }
    }

    listenTo(updateButton, cleanButton)

    //ui update
    val actorSystem = ActorSystem("NetAssetActorSystem")
    val netAssetActor = actorSystem.actorOf(Props(new NetAssetActor(valuesTable, netAssetLabel, dateLabel, updateButton)), "netAssetActor")

    reactions += {
      case ButtonClicked(updateButton) if updateButton.text == "Update" => {
        updateButton.enabled = false
        netAssetActor ! "start"
      }

      case ButtonClicked(cleanButton) => cleanTable
    }

    def cleanTable = {
      for (i <- 0 until valuesTable.rowCount) {
        valuesTable(i, 2) = "?"
        valuesTable(i, 3) = "?"
      }
    }
  }

}