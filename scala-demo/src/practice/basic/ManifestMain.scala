package practice.basic

import scala.reflect.runtime.universe._

object ManifestMain extends App {
  
  def paramInfo[T](x: T)(implicit tag: TypeTag[T]): Unit = {
    val targs = tag.tpe match { case TypeRef(_, _, args) => args }
    println(s"type of $x has type arguments $targs")
  }
  
  def paramInfo1[T: TypeTag](x: T) = {
    val targs = typeOf[T] match { case TypeRef(_, _, args) => args }
    println(s"type of $x has type arguments $targs")
  }

  paramInfo(List("one", "two"))
  paramInfo(List(1, 2))
  paramInfo(List("one", 2))

  paramInfo1(List("one", "two"))
  paramInfo1(List(1, 2))
  paramInfo1(List("one", 2))

}