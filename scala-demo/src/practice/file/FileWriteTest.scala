package practice.file

import java.io.PrintWriter

object FileWriteTest {
  val file = FileTest getFile

  def main(args: Array[String]) {
    val writer = new PrintWriter(file)
    writer.append("Scala File Demo 中文")
    writer.append("Scala File Demo 中文")
    writer.close()
  }
}
