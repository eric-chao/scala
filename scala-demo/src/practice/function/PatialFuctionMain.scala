package practice.function

object PatialFuctionMain extends App {
  
  val f = (a: Int, b: String, c: String) => b * a + c
  
  val f2 = f(5, _:String, _:String)
  
  println(f2("A", "B"))
  
  val f3 = f(_: Int, "A", _:String)
  
  println(f3(5, "A"))
  
  println("C" * 3 * 2)
}