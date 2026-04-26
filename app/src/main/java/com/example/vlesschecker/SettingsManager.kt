package com.example.vlesschecker

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsManager(private val context: Context) {
    companion object {
        private val THREAD_COUNT = intPreferencesKey("thread_count")
        private val TIMEOUT_MS = intPreferencesKey("timeout_ms")
        private val PARSING_URL = stringPreferencesKey("parsing_url")

        private const val DEFAULT_THREAD_COUNT = 4
        private const val DEFAULT_TIMEOUT_MS = 1500
        private const val DEFAULT_PARSING_URL =
            "https://raw.githubusercontent.com/zieng2/wl/refs/heads/main/vless_lite.txt"
    }

    val threadCountFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[THREAD_COUNT] ?: DEFAULT_THREAD_COUNT
    }

    val timeoutMsFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[TIMEOUT_MS] ?: DEFAULT_TIMEOUT_MS
    }

    val parsingUrlFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PARSING_URL] ?: DEFAULT_PARSING_URL
    }

    suspend fun setThreadCount(count: Int) {
        context.dataStore.edit { preferences ->
            preferences[THREAD_COUNT] = count
        }
    }

    suspend fun setTimeoutMs(timeout: Int) {
        context.dataStore.edit { preferences ->
            preferences[TIMEOUT_MS] = timeout
        }
    }

    suspend fun setParsingUrl(url: String) {
        context.dataStore.edit { preferences ->
            preferences[PARSING_URL] = url
        }
    }
}

