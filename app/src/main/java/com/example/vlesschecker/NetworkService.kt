package com.example.vlesschecker

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetAddress
import java.util.concurrent.Executors

class NetworkService(threadCount: Int = 4, private val timeoutMs: Int = 1500) {
    private var executor = Executors.newFixedThreadPool(threadCount)

    fun updateThreadPool(threadCount: Int) {
        executor.shutdown()
        executor = Executors.newFixedThreadPool(threadCount)
    }

    suspend fun isReachable(ip: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val inet = InetAddress.getByName(ip)
            inet.isReachable(timeoutMs)
        } catch (e: Exception) {
            false
        }
    }
}
