package practice.array

object IteratorTest {
  
  def main(args: Array[String]) {
    val it = Iterator("baidu", "google", "runoob", "taobao")
    
    //println("max value is: " + it.max)
    println("min value is: " + it.min)
    
    while (it.hasNext) {
      println(it.next())
    }
    

  }
  
}