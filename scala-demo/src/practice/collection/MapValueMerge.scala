package practice.collection

object MapValueMerge extends App {
  val m = Map(2 -> (3, 2), 1 -> (2, 1, 3))
  val m2 = m.mapValues(_.productIterator.map(_.asInstanceOf[Int]).sum)
  //Map(2 -> 5, 1 -> 6)
  println(m2)
}