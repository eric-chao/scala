package com.akkademy.askdemo

import akka.actor.Actor
import com.akkademy.Messages.ArticleBody
import com.akkademy.Messages.GetRequest
import com.akkademy.Messages.HttpResponse
import com.akkademy.Messages.ParseArticle
import com.akkademy.Messages.ParseHtmlArticle

class ParsingActor extends Actor {
  override def receive: Receive = {
    case ParseHtmlArticle(key, html) =>
      sender() ! ArticleBody(key, de.l3s.boilerpipe.extractors.ArticleExtractor.INSTANCE.getText(html))
    case x =>
      println("unknown message " + x.getClass)
  }
}