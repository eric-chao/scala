package practice.netasset

import MainProtocol.InitSignal
import akka.actor.Actor

class MainPrintChildActor extends Actor {

  //implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(5))
  override def preStart() {
    println("[child] start")
  }

  def receive = {
    case signal: InitSignal =>
      val symbol = signal.symbol
      val latestClosingPrice = StockPriceFinder.getLastestClosingPrice(symbol)
      val units = NetAssetStockPriceHelper.symbolsAndUnits(symbol)
      val value = units * latestClosingPrice

      sender ! 1l
      println(" %-7s %-5d %-16f %f".format(symbol, units, latestClosingPrice, value))
  }

  override def postStop() {
    println("[child] stop")
  }
}