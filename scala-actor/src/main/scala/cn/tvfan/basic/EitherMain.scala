package cn.tvfan.basic

import java.net.URL
import scala.io.Source

object EitherMain {

  def getContent(url: URL): Either[String, Source] =
    if (url.getHost.contains("google"))
      Left("Requested URL is blocked for the good of the people!")
    else
      Right(Source.fromURL(url))

  def main(args: Array[String]) {
    getContent(new URL("http://www.baidu.com")) match {
      case Left(msg)     => println(msg)
      case Right(source) => source.getLines.foreach(println)
    }

    val content: Either[String, Iterator[String]] =
      getContent(new URL("http://danielwestheide.com")).right.map(_.getLines())
      
    val moreContent: Either[String, Iterator[String]] =
      getContent(new URL("http://google.com")).right.map(_.getLines)
  }
}