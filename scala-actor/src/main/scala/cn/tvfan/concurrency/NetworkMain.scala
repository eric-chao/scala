package cn.tvfan.concurrency

object NetworkMain extends App {
  (new NetworkService(2020, 5)).run
}