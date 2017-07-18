package practice.exception

import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException

object ExceptionTest {

  def main(args: Array[String]) {
    try {
      val f = new FileReader("D:/Third-Party/Zip/nginx.conf")
      println(f getEncoding)
    } catch {
      case ex: FileNotFoundException => println("[File] missing file exception")
      case ex: IOException => println("[File] IO Exception")
    } finally {
      println("[File] Exiting finally...")
    }
  }
}