package practice.matchtest

object SealedMatchTest extends App {
  
  sealed trait A
  
  case class B(x: Int, y: Int) extends A
  case class C(x: Int) extends A
  case class D(x: Int) extends A
  
  val a: A = B(1, 3)
  
  a match {
    //case B(x: Int, y: Int) => println(x + y)
    case e @ B(_, _) => println(e.x + e.y)
    case e @ C(_) => println(e)
    case e @ D(_) => println(e)
  }
}