package com.example.vlesschecker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.net.InetAddress

data class WhitelistCheckResult(
    val vkReachable: Boolean,
    val yandexReachable: Boolean,
    val githubReachable: Boolean,
    val googleReachable: Boolean,
    val maxReachable: Boolean,
    val whitelistDetected: Boolean
)

class WhitelistMonitor(private val context: Context, private val timeoutMs: Int = 1500) {
    companion object {
        private const val CHANNEL_ID = "whitelist_monitor"
        private const val NOTIFICATION_ID = 42
        private const val CHECK_INTERVAL_MS = 5 * 60 * 1000L

        private val DOMAINS = listOf(
            "vk.com",
            "yandex.ru",
            "github.com",
            "google.com",
            "max.ru"
        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Whitelist Monitor",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications about whitelist status"
            }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private suspend fun pingDomain(domain: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val inet = InetAddress.getByName(domain)
            inet.isReachable(timeoutMs)
        } catch (e: Exception) {
            false
        }
    }

    suspend fun checkWhitelist(): WhitelistCheckResult = withContext(Dispatchers.IO) {
        val vkReachable = pingDomain("vk.com")
        val yandexReachable = pingDomain("yandex.ru")
        val githubReachable = pingDomain("github.com")
        val googleReachable = pingDomain("google.com")
        val maxReachable = pingDomain("max.ru")

        val whitelistDetected = vkReachable && yandexReachable && maxReachable &&
                                !githubReachable && !googleReachable

        WhitelistCheckResult(
            vkReachable = vkReachable,
            yandexReachable = yandexReachable,
            githubReachable = githubReachable,
            googleReachable = googleReachable,
            maxReachable = maxReachable,
            whitelistDetected = whitelistDetected
        )
    }

    fun notifyWhitelistDetected() {
        createNotificationChannel()
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Whitelist Detected")
            .setContentText("Whitelist filtering has been applied to your network")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }

    fun monitorPeriodically(): Flow<WhitelistCheckResult> = flow {
        while (true) {
            try {
                val result = checkWhitelist()
                emit(result)
                if (result.whitelistDetected) {
                    notifyWhitelistDetected()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            delay(CHECK_INTERVAL_MS)
        }
    }
}

