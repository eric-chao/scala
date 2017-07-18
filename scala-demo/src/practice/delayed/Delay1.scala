package practice.delayed

class Delay1[A](v: => A) {

  private var isFirstRun = true
  private var value: A = _

  def compute(): A = {
    if (isFirstRun) {
      value = v
      isFirstRun = false
    }
    
    value
  }
} 