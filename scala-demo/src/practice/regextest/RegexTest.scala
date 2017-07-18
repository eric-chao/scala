package practice.regextest

import scala.util.matching.Regex

object RegexTest {
  def main(args: Array[String]) {
    val pattern = "Scala".r
    val p1 = "(S|s)cala".r
    val str = "Scala is scalable and cool"

    println(pattern findFirstIn str)
    println(pattern replaceFirstIn (str, "Java"))
    println(pattern replaceAllIn (str, "Java"))
    println((pattern findAllIn str).mkString(", "))
    
    println(p1 replaceAllIn (str, "Java"))
    println((p1 findAllIn str).mkString(", "))
  }
}