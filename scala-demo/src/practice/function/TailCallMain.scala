package practice.function

object TailCallMain extends App {

  //not tail call cause boom(x - 1) + 1
  def boom(x: Int): Int = {
    if (x == 0) throw new Exception("boom!")
    else boom(x - 1) + 1
  }

  //tail call cause bang(x - 1)
  def bang(x: Int): Int = {
    if (x == 0) throw new Exception("bang!")
    else bang(x - 1)
  }
  
  //println(boom(5))
  
  println(bang(5))
}