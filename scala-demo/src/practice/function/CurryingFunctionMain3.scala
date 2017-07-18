package practice.function

object CurryingFunctionMain3 extends App {

  def filter(xs: List[Int], p: Int => Boolean): List[Int] = {
    if (xs.isEmpty) xs
    else if (p(xs.head)) xs.head :: filter(xs.tail, p)
    else filter(xs.tail, p)
  }

  def modN(n: Int)(x: Int) = ((x % n) == 0)
  val nums = List(1, 2, 3, 4, 5, 6, 7, 8)

  println("head: " + nums.head)
  println("tail: " + nums.tail)

  println(filter(nums, modN(2)(_)))
  println(filter(nums, modN(3)))
  println(filter(List(), modN(3)))
}