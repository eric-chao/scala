package practice.delayed

class Delay[A](v: => A) {
  def compute() = v
} 