package practice.matchtest

object ClassTest {
  case class Person(name: String, age: Int)

  def main(args: Array[String]) {
    val alice = Person("Alice", 25)
    val bob = Person("Bob", 32)
    val charlie = Person("Charlie", 32)

    for (p <- List(alice, bob, charlie)) {
      p match {
        case Person("Alice", 25) => println("Hi Alice!")
        case Person("Bob", 32) => println("Hi Bob!")
        case Person(name, age) => println("Age: " + age + " year, name: " + name + "?")
      }
    }
    
    val persons = List(("Alice", 25), ("Bob", 32), ("Charlie", 32))
    for (p <- persons) {
      p match {
        case (name, age) => println("Age: " + age + " year, name: " + name + "?")
        case _ => println("无法计算")
      }

    }
  }
}