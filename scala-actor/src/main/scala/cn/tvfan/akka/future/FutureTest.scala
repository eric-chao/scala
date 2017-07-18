package cn.tvfan.akka.future

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Failure
import scala.util.Success

object FutureTest extends App {
  val s = "Hello"
  val f: Future[String] = Future[String] {
    s + " future!"
  }

//  f onSuccess {
//    case msg => println(msg)
//  }

  f onComplete {
    case Success(t) =>
      {
        println(t)
      }
    case Failure(e) =>
      {
        println(s"An error has occured: $e.getMessage")
      }
  }
  println("dd")

  //println(s) //不加这句, f onSuccess就不执行
}