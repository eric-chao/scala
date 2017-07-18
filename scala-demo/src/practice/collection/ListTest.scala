package practice.collection

object ListTest {

  val mainList = List(3, 2, 1)

  val with4 = 4 :: mainList
  val with42 = 42 :: mainList
  val shorter = mainList.tail

  def main(args: Array[String]) {
    mainList.foreach { x => print(x) }

    printSeparator
    with4.foreach { x => print(x) }
    
    printSeparator
    shorter.foreach { print(_) }
    
    printSeparator
    val c = (0 /: (1 to 3))((x, y) => x + y)
    println(c)
  }

  def printSeparator() {
    println
    println("-----------------")
  }
}