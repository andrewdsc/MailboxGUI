package com.andrew.mailbox.test

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    val host = "127.0.0.1"      // 主机是本机
    val port = 2333     // 使用 2333 端口
    try {
        // 开启服务端
        Thread {
            Server(port).update()
        }.start()
        // 开启客户端，纯客户端需要删除上面的服务端
        Thread {
            ClientSocket(host, port).update()
        }.start()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

class Server(port: Int) : ServerSocket(port) {
    var clientList = mutableListOf<ClientHandler>()
    fun update() {
        while (true) {
            val client = accept()
            clientList.add(ClientHandler(client, this).build())
        }
    }

    fun sendMessage(msg: String) {
        for (x in clientList) {
            x.write(msg)
        }
    }

    class ClientHandler(client: Socket, var target: Server) : Thread() {

        var reader = BufferedReader(InputStreamReader(client.getInputStream()))
        var writer = PrintWriter(client.getOutputStream(), true)    // true 表示自动flush，不写true的话需要手动flush()


        fun build(): ClientHandler {
            start()
            return this
        }

        override fun run() {
            while (true) {
                val line = reader.readLine()
                var lpr_text = line.replace("\r\n", "").split("_")
                if (lpr_text[0] == "2")
                {
                    val c01:Colors = Colors.BLUE
                    target.sendMessage("${lpr_text[0].toString()}:+$c01")
                }

            }
        }

        fun write(msg: String) {
            writer.println("用户：$msg")
        }
    }
}

// 客户端
class ClientSocket(host: String, port: Int) : Socket(host, port) {
    var reader = BufferedReader(InputStreamReader(inputStream))
    var writer = PrintWriter(outputStream, true)

    fun update() {
        ReadHandler(reader).build()
        while (true) {
            // 从控制台读入
            val msg = readLine()
            if (msg == "exit") exitProcess(0)
            // 向服务器写入
            writer.println(msg)
        }
    }

    // 开启一个线程从服务器读取数据
    class ReadHandler(var reader: BufferedReader) : Thread() {
        fun build(): ReadHandler {
            start()
            return this
        }

        override fun run() {
            while (true) {
                // 从服务器读取一行
                val line = reader.readLine()
                // 向控制台写入
                println(line)
            }
        }
    }
}