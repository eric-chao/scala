package practice.array
import Array._

object ArrayTest {

  val array = apply(1, 2, 3)

  val myArray0 = range(5, 10)
  val myArray1 = Array(1, 2, 3, 4)
  val myArray = concat(myArray0, myArray1)
  
  def add1(x: Int) = { x + 1 }
  
  //(_ + 1) equals to (a => a + 1)
  val list1 = Array.iterate(0, 3)(add1)

  val list2 = Array.tabulate(3)(_ + 1)
  
  def main(args: Array[String]) {
    for (x <- array) {
      print(x)
    }
    println()
    for (x <- myArray) {
      print(x)
    }    
    println()
    for (x <- list1) {
      print(x)
    } 
    println()
    for (x <- list2) {
      print(x)
    } 
  }
}