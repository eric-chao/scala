package practice.netasset

import akka.actor.Actor
import akka.actor.ActorRef

object NetAssetStockPriceHelper {

  val symbolsAndUnits = StockPriceFinder.getTikersAndUnits

  def getInitialTableValues: Array[Array[Any]] = {
    val emptyArrayOfArrayOfAny = new Array[Array[Any]](0)
    (emptyArrayOfArrayOfAny /: symbolsAndUnits) {
      (data, element) =>
        val (symbol, units) = element
        data ++ Array(List(symbol, units, "?", "?").toArray)
    }
  }

}
