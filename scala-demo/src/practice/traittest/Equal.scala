package practice.traittest

trait Equal {
  def isEqual(x: Any) : Boolean
  def isNotEqual(x: Any): Boolean = !isEqual(x)
}

class Point(xc: Int, yc: Int) extends Equal {
  var x: Int = xc
  var y: Int = yc
  
  def isEqual(obj: Any): Boolean = {
    obj.isInstanceOf[Point] && 
    obj.asInstanceOf[Point].x == x &&
    obj.asInstanceOf[Point].y == y
  }
}

object Test {
  
  def main(args: Array[String]) {
    val p1 = new Point(2, 3)
    val p2 = new Point(2, 3)
    val p3 = new Point(3, 3)
    
    println(p1.isEqual(p2))
    println(p1.isEqual(p3))
    println(p1.isEqual(2))
  }
}