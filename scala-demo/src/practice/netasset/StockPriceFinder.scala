package practice.netasset

import java.util.Calendar

import scala.collection.JavaConversions.mapAsScalaMap
import scala.collection.mutable.Map

import scala.io.Source
import scala.xml.XML
import java.util.concurrent.ConcurrentHashMap

object StockPriceFinder {

  def main(args: Array[String]) {
    println(Calendar.getInstance.getWeekYear)
    println(getTikersAndUnits.size)
    println(getLastestClosingPrice("AAPL"))
  }

  def getLastestClosingPrice(symbol: String) = {
    val url = "http://chart.finance.yahoo.com/table.csv?s=" + symbol + "&a=9&b=18&c=" + Calendar.getInstance.getWeekYear
    val data = Source.fromURL(url).mkString
    val mostRecentData = data.split("\n")(1)
    val closingPrice = mostRecentData.split(",")(4).toDouble
    closingPrice
  }

  def getTikersAndUnits = {
    val stocksAndUnitsXML = XML.load("symbols.xml")
    val initmap: Map[String, Int] = new ConcurrentHashMap[String, Int]

    (initmap /: (stocksAndUnitsXML \ "symbol")) {
      (map, symbolNode) =>
        val ticker = (symbolNode \ "@ticker").toString
        val units = (symbolNode \ "units").text.toInt
        map(ticker) = units //creates and returns a new map
        map
    }
  }
}