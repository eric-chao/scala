package cn.tvfan.akka.university

object TeacherProtocol {
  
  case class QuoteRequest()
  case class QuoteResponse(quoteString: String)
  
}