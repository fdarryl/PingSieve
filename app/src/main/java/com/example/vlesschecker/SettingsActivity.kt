package com.example.vlesschecker

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vlesschecker.ui.theme.VlessCheckerTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VlessCheckerTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Settings") },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF1976D2),
                                titleContentColor = Color.White,
                                navigationIconContentColor = Color.White
                            )
                        )
                    }
                ) { paddingValues ->
                    SettingsScreen(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val settingsManager = remember { SettingsManager(context) }

    val threadCount by settingsManager.threadCountFlow.collectAsState(4)
    val timeoutMs by settingsManager.timeoutMsFlow.collectAsState(1500)
    val parsingUrl by settingsManager.parsingUrlFlow.collectAsState("https://raw.githubusercontent.com/zieng2/wl/refs/heads/main/vless_lite.txt")

    var threadCountInput by remember { mutableStateOf(threadCount.toString()) }
    var timeoutInput by remember { mutableStateOf(timeoutMs.toString()) }
    var urlInput by remember { mutableStateOf(parsingUrl) }

    LaunchedEffect(threadCount) {
        threadCountInput = threadCount.toString()
    }

    LaunchedEffect(timeoutMs) {
        timeoutInput = timeoutMs.toString()
    }

    LaunchedEffect(parsingUrl) {
        urlInput = parsingUrl
    }

    val versionName = try {
        context.packageManager.getPackageInfo(context.packageName, 0).versionName ?: "Unknown"
    } catch (e: PackageManager.NameNotFoundException) {
        "Unknown"
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFF121212))
    ) {
        Text(
            text = stringResource(R.string.settings_general),
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 8.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                SettingItem(
                    label = stringResource(R.string.settings_thread_count),
                    subtitle = stringResource(R.string.settings_thread_count_summary)
                )
                TextField(
                    value = threadCountInput,
                    onValueChange = { newValue ->
                        threadCountInput = newValue
                        val count = newValue.toIntOrNull()
                        if (count != null && count > 0) {
                            coroutineScope.launch {
                                settingsManager.setThreadCount(count)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    label = { Text("Threads") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF2A2A2A),
                        unfocusedContainerColor = Color(0xFF2A2A2A),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFF3A3A3A))

                SettingItem(
                    label = stringResource(R.string.settings_timeout),
                    subtitle = stringResource(R.string.settings_timeout_summary)
                )
                TextField(
                    value = timeoutInput,
                    onValueChange = { newValue ->
                        timeoutInput = newValue
                        val timeout = newValue.toIntOrNull()
                        if (timeout != null && timeout > 0) {
                            coroutineScope.launch {
                                settingsManager.setTimeoutMs(timeout)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    label = { Text("Milliseconds") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF2A2A2A),
                        unfocusedContainerColor = Color(0xFF2A2A2A),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFF3A3A3A))

                SettingItem(
                    label = stringResource(R.string.settings_parsing_url),
                    subtitle = stringResource(R.string.settings_parsing_url_summary)
                )
                TextField(
                    value = urlInput,
                    onValueChange = { newValue ->
                        urlInput = newValue
                        if (newValue.isNotEmpty()) {
                            coroutineScope.launch {
                                settingsManager.setParsingUrl(newValue)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    label = { Text("URL") },
                    singleLine = false,
                    maxLines = 2,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF2A2A2A),
                        unfocusedContainerColor = Color(0xFF2A2A2A),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFF3A3A3A))

                SettingItem(
                    label = stringResource(R.string.settings_app_version),
                    subtitle = versionName
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun SettingItem(
    label: String,
    subtitle: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.White
        )
        Text(
            text = subtitle,
            fontSize = 12.sp,
            color = Color(0xFFB0B0B0)
        )
    }
}
