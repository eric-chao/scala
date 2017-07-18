package practice.function

import java.util.Calendar

object TailCallMain2 extends App {
  //递归
  def fib(n: Int): Int = n match {
    case 0 => 0//throw new Exception("fib!")
    case 1 => 1
    case _ => fib(n - 1) + fib(n - 2)
  }

  //尾递归
  def fib2(a: Int, b: Int, n: Int): Int = n match {
    case 0 => b//throw new Exception("fib2!")
    case _ => fib2(b, a + b, n - 1)
  }

  val start = Calendar.getInstance.getTimeInMillis;
  println(fib(7))
  val end1 = Calendar.getInstance.getTimeInMillis
  println(end1 - start)
  println(fib2(1, 0, 7))
  val end2 = Calendar.getInstance.getTimeInMillis
  println(end2 - end1)
}