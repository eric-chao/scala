package practice.delayed

class Book(name: String) {
  println("New Book " + name)
  override def toString() = "《" + name + "》"
}

object BookMain extends App {
  lazy val b = new Book("java")
  
  println("-------------------")
  println(b.toString())
}