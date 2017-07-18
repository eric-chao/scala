package practice.function

object CurryingFunctionMain2 extends App {
  
  def curriedSum(x: Int, y: Int) = {
    x + y
  }
  
  def first(x:Int)(y:Int) = {
    println("cal ...")
    x + y
  }
  
//  def first(x:Int) = (y:Int) => {
//    x + y
//  }
  
  val second = first(1)(_)
  
  println(second(2))
}