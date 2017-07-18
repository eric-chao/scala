package practice.delayed

object DelayTestApp extends App {

  def delay[A](v: => A) = { () => v }
  def compute[A](dv: () => A) = dv()

}