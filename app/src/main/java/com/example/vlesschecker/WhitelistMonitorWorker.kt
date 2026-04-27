package com.example.vlesschecker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit

class WhitelistMonitorWorker(
    context: Context,
    params: androidx.work.WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val settingsManager = SettingsManager(applicationContext)
            val isEnabled = settingsManager.whitelistMonitoringEnabledFlow.first()

            if (isEnabled) {
                val timeoutMs = settingsManager.timeoutMsFlow.first()
                val monitor = WhitelistMonitor(applicationContext, timeoutMs)
                val result = monitor.checkWhitelist()

                if (result.whitelistDetected) {
                    monitor.notifyWhitelistDetected()
                }
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }

    companion object {
        private const val WORK_TAG = "whitelist_monitor_work"

        fun scheduleMonitoring(context: Context) {
            val whitelistMonitorRequest = PeriodicWorkRequestBuilder<WhitelistMonitorWorker>(
                15,
                TimeUnit.MINUTES
            ).build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_TAG,
                ExistingPeriodicWorkPolicy.KEEP,
                whitelistMonitorRequest
            )
        }

        fun stopMonitoring(context: Context) {
            WorkManager.getInstance(context).cancelAllWorkByTag(WORK_TAG)
        }
    }
}

