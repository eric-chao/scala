package practice.collection

object TupleTest extends App {
  val t = (1, 3.14, "Fred")
  
  val (first, second, third) = t
  val (f1, f2, _) = t
  println(first + " " + f1)
  println(second + " " + f2)
  println(third)
}