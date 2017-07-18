package practice.file

import scala.io.Source
import java.util.Scanner
import java.io.File

object FileReadTest {
  val file = FileTest getFile

  def main(args: Array[String] ) {
    println("文件内容为:")
    Source.fromFile(file).foreach {
      print
    }
  }

  def tokenCount(file: File) = {
    val in = new Scanner(file)
    val map = new scala.collection.mutable.HashMap[String, Int]
    while (in.hasNext()) {
      val token = in.next()
      //map += (token -> (map.getOrElse(token, 0) + 1))
      map(token) = map.getOrElse(token, 0) + 1
    }
    map
  }

  println(tokenCount(new File(file)).mkString(" ; "))

  def tokenCount(fileName: String) = {
    val tokens = Source.fromFile(fileName).mkString.split("\\s+")
    val map = new scala.collection.mutable.HashMap[String, Int]
    for (token <- tokens) 
      //map += (token -> (map.getOrElse(token, 0) + 1))
      map(token) = map.getOrElse(token, 0) + 1
    map
  }
  
  println(tokenCount(file).mkString(" ; "))
}