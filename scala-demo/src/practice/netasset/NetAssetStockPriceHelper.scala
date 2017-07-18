package practice.netasset

//import scala.actors._
//import Actor._
//
//object NetAssetStockPriceHelper {
//
//  val symbolsAndUnits = StockPriceFinder.getTikersAndUnits
//
//  def getInitialTableValues: Array[Array[Any]] = {
//    val emptyArrayOfArrayOfAny = new Array[Array[Any]](0)
//    (emptyArrayOfArrayOfAny /: symbolsAndUnits) {
//      (data, element) =>
//        val (symbol, units) = element
//        data ++ Array(List(symbol, units, "?", "?").toArray)
//    }
//  }
//
//  def fetchPrice(updater: Actor) = actor {
//    val caller = self
//
//    symbolsAndUnits.keys.foreach { symbol =>
//      actor { caller ! (symbol, StockPriceFinder.getLastestClosingPrice(symbol)) }
//    }
//
//    val netWorth = (0.0 /: (1 to symbolsAndUnits.size)) {
//      (worth, index) =>
//        receiveWithin(10000) {
//          case (symbol: String, latestClosingPrice: Double) =>
//            val units = symbolsAndUnits(symbol)
//            val value = units * latestClosingPrice
//            updater ! (symbol, units, latestClosingPrice, value)
//
//            worth + value
//        }
//    }
//
//    updater ! netWorth
//  }
//
//}