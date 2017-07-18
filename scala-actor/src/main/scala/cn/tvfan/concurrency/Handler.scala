package cn.tvfan.concurrency

import java.net.Socket

class Handler(socket: Socket) extends Runnable {
  
  def message = (Thread.currentThread().getName + "\n").getBytes
  
  def run(): Unit = {
    socket.getOutputStream.write(message)
    socket.getOutputStream.close()
  }
}