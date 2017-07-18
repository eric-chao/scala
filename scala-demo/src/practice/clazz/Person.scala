package practice.clazz

class Person(val name: String) {

  override def toString = getClass.getName + "[name=" + name + "]"
}

class Employee(override val name: String, val salary: Int) extends Person(name: String) {

  override def toString = super.toString() + "[salary=" + salary + "]"
}

object Person {
  def apply(name: String): String = {
    name.concat("xxx")
  }

  def unapply(name: String): Option[String] = {
    Some(name.substring(0, name.length() - 3))
  }

}

object Test extends App {
  val fred = new Employee("Fred", 50000)
  println(fred)
}