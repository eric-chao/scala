package com.akkademy

object Messages {
  case class SetRequest(key: String, value: Object)
  case class GetRequest(key: String)
  case class KeyNotFoundException(key: String) extends Exception

  case class ParseArticle(url: String)
  case class ParseHtmlArticle(url: String, htmlString: String)
  case class HttpResponse(body: String)
  case class ArticleBody(url: String, body: String)
}