package practice.clazz

//singleton
class Marker private(val color: String){
  println("[obj created] " + this)
  
  override def toString = "color id: " + color
}

object Marker{
  private val markers = Map (
    "red" -> new Marker("red"),
    "blue" -> new Marker("blue"),
    "green" -> new Marker("green")
  )
  
  val xxx = Some(new Marker("xxx"))
  def apply(color: String) = {
    if(markers.contains(color)) Some(markers(color)) get else xxx get
  }
  
  def getMarker(color: String) = {
      if(markers.contains(color)) markers(color) else null
  }

}