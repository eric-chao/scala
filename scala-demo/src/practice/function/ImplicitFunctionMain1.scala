package practice.function

object ImplicitFunctionMain1 extends App {
  implicit def wrapper(s: String) = {
    new { def wrap = "--" + s + "--" }
  }
  
  val s = "Hello"
  println(s.wrap)
}
