package practice.netasset

import scala.actors._
import Actor._

object FindTotalWorthConcurrent extends App {
  val symbolsAndUnits = StockPriceFinder.getTikersAndUnits

  val caller = self

  println("Today is " + new java.util.Date)
  println("Tocker Units Closing Price($) Total Value($)")

  val startTime = System.currentTimeMillis()
  symbolsAndUnits.keys.foreach {
    symbol => actor { caller ! (symbol, StockPriceFinder.getLastestClosingPrice(symbol)) }
  }

  val netWorth = (0.0 /: (1 to symbolsAndUnits.size)) {
    (worth, index) =>
      receiveWithin(10000) {
        case (symbol: String, latestClosingPrice: Double) =>
          val units = symbolsAndUnits(symbol)
          val value = units * latestClosingPrice

          println("%-7s %-5d %-16f %f".format(symbol, units, latestClosingPrice, value))
          worth + value
        case _ => 0.0
      }
  }

  val endTime = System.currentTimeMillis()
  println("The total value of your investments is $" + netWorth)
  println("Took %f seconds".format((endTime - startTime) / 1000.0))
}