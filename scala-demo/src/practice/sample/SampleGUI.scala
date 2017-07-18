package practice.sample

import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import scala.swing.Label
import scala.swing.Button
import scala.swing.FlowPanel
import scala.swing.event.ButtonClicked

object SampleGUI extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "A Sample Of Scala Swing GUI"
    
    val label = new Label { text = "------" }
    val button = new Button { text = "Click ME"}
    
    contents = new FlowPanel {
      contents += label
      contents += button
    }
    
    listenTo(button)
    
    reactions +=  {
      case ButtonClicked(button) => 
        label.text = "You Clicked!"
    }
  }
}