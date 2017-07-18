package practice.matchtest

object StringTest {
  def main(args: Array[String]) {
    val x = StringTest("5")
    println(x)

    x match {
      case StringTest(num) => println(x + " is two times of " + num)
      case _               => println("can't caculate")
    }
  }

  def apply(x: String) = x.concat("xxx")

  def unapply(x: String): Option[String] = Some(x.substring(0, x.length() - 3))
}