package practice.netasset

object MainProtocol {
  sealed class Signal
  case class InitSignal(symbol: String) extends Signal
  case class ShutDown() extends Signal
}