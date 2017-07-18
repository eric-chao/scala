package practice.function

object CurringFunctionMain extends App {

  val f = (a: Int, b: String, c: String) => {
    println("call origin!")
    b * a + c
  }

  val curry = f.curried(5)(_: String)(_: String)
  
  println("--------------------------")
  println(curry("A", "B"))
  println("--------------------------")
  println(curry("C", "B"))
}