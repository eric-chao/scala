package practice.matchtest

object Test {
  def main(args: Array[String]) {
    val x = Test(5)
    println(x)

    x match {
      case Test(num) => println(x + " is two times of " + num)
      case _         => println("can't caculate")
    }
  }

  def apply(x: Int) = x * 2

  def unapply(x: Int): Option[Int] = if (x % 2 == 0) Some(x / 2) else None
}