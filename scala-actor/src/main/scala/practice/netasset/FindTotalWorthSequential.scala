package practice.netasset

object FindTotalWorthSequential extends App {
  
  val symbolsAndUnits = StockPriceFinder.getTikersAndUnits
  println("Today is " + new java.util.Date)
  println("Ticker Units Closing Price($) Total Value($)")
  
  val startTime = System.nanoTime()
  
  val netWorth: Double = (0.0 /: symbolsAndUnits) {
    (worth, symbolAndUnit) => 
      val (symbol, units) = symbolAndUnit
      val latestClosingPrice =  StockPriceFinder.getLastestClosingPrice(symbol)
      val value = units * latestClosingPrice
      println("%-7s %-5d %-16f %f".format(symbol, units, latestClosingPrice, value))
      
      worth + value
  }
  
  val endTime = System.nanoTime()
  
  println("The total value of your investments is $" + netWorth)
  println("Took %f seconds".format((endTime - startTime)/1000000000.0))
}