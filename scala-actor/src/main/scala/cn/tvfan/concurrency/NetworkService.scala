package cn.tvfan.concurrency

import java.net.ServerSocket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NetworkService(port: Int, poolSize: Int) extends Runnable {
  val serverSocket = new ServerSocket(port)
  val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)

  def run(): Unit = {
    try {
      while (true) {
        // This will block until a connection comes in.
        val socket = serverSocket.accept()
        //(new Handler(socket)) run
        //(new Thread(new Handler(socket))).start()
        pool.execute(new Handler(socket))
      }
    } finally {
      pool.shutdown()
    }

  }
}