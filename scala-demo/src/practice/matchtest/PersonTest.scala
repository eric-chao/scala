package practice.matchtest

import practice.clazz.Person

object PersonTest {
  def main(args: Array[String]) {
    val alice = Person("Alice")
    val bob_m = Person("Bob M")
    val charlie = Person("charlie")
    //println(alice)
    for (p <- List(alice, bob_m, charlie)) {
      p match {
        case Person(name) => println( name + " is the origin of " + p)
      }
    }

  }
}