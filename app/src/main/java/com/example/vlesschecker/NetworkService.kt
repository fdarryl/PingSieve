package com.example.vlesschecker

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetAddress

class NetworkService {

    suspend fun isReachable(ip: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val inet = InetAddress.getByName(ip)
            inet.isReachable(1500)
        } catch (e: Exception) {
            false
        }
    }
}
