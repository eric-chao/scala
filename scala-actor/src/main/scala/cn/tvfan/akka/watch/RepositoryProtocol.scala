package cn.tvfan.akka.watch

object RepositoryProtocol {
  case class QuoteRequest()
  case class QuoteRepositoryRequest()
  case class QuoteRepositoryResponse(quoteString: String)
}