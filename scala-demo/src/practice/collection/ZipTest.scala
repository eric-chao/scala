package practice.collection

object ZipTest extends App {

  val symbols = Array("<", "-", ">")
  val counts = Array(2, 10, 2)
  val pairs = symbols.zip(counts)
  
  pairs.foreach(f => println(f))
}