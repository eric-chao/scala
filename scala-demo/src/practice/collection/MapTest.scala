package practice.collection

import java.util.TreeMap

import scala.annotation.migration
import scala.collection.JavaConversions.mapAsScalaMap
import scala.collection.JavaConversions.propertiesAsScalaMap
import scala.collection.mutable.Map
import scala.io.Source

import practice.file.FileTest

//scala.collection.mutable.LinkedHashMap

//scala.collection.immutable.SortedMap

//java conversion
//scala.collection.JavaConversions.mapAsJavaMap   //Scala Map -> Java Map
//scala.collection.JavaConversions.mapAsScalaMap  //Java Map -> Scala Map
//scala.collection.JavaConversions.propertiesAsScalaMap //Java Properties -> Scala Map
object MapTest extends App {

  def tokenCount(fileName: String) = {
    val tokens = Source.fromFile(fileName).mkString.split("\\s+")
    val map: Map[String, Int] = new TreeMap[String, Int]
    for (token <- tokens)
      map(token) = map.getOrElse(token, 0) + 1
    map
  }

  println(tokenCount(FileTest getFile ()).mkString(" ; "))

  def printJavaProperties() = {
    val properties: scala.collection.Map[String, String] = System.getProperties()
    val maxLength = properties.keySet.map(_.length).max
    for ((key, value) <- properties) {
      print(key)
      print(" " * (maxLength - key.length))
      print("|")
      println(value)
    }
  }

  printJavaProperties

  val persons = Map("Alice" -> 25, "Bob" -> 32, "Cindy" -> 32)

  val p1 = Map(("Alice", 25), ("Bob", 32), ("Cindy", 32))

  print(persons("Alice") + " " + p1("Alice") + " " + p1.getOrElse("Charlie", 0))
  println
  println("-------------------------------")

  val p2 = p1 + ("David" -> 48)
  p2.foreach(p => print(p + " "))

  println
  println("-------------------------------")

  val p3 = p1 - ("Cindy")
  p3.foreach(p => print(p + " "))

}