package practice

object ClosureTest {
  val factor = 3
  val multiplier = (i: Int) => i * factor

  def main(args: Array[String]) {
    println("multiplier(1) value = " + multiplier(1))
    println("multiplier(2) value = " + multiplier(2))

    //more是一个自由变量，其值及类型是在运行的时候得以确定的  
    //x是类型确定的，其值是在函数调用的时候被赋值的  
    //这样的函数称之为闭包：从开放到封闭的过程  
    def add(more: Int) = (x: Int) => x + more

    //给more赋值  
    val add1 = add(1)
    //调用add函数  
    println(add1(100))
  }

}