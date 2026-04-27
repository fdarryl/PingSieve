package com.example.vlesschecker

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.vlesschecker.ui.theme.VlessCheckerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.net.URL

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VlessCheckerTheme {
                App()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("PingSieve") },
                actions = {
                    IconButton(onClick = { context.startActivity(Intent(context, SettingsActivity::class.java)) }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                    IconButton(onClick = { context.startActivity(Intent(context, AboutActivity::class.java)) }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "About")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF06173A),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        MainScreen(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val parser = remember { Parser() }
    val settingsManager = remember { SettingsManager(context) }
    var networkService by remember { mutableStateOf(NetworkService(4, 1500)) }

    val threadCount by settingsManager.threadCountFlow.collectAsState(4)
    val timeoutMs by settingsManager.timeoutMsFlow.collectAsState(1500)
    val parsingUrl by settingsManager.parsingUrlFlow.collectAsState("https://raw.githubusercontent.com/zieng2/wl/refs/heads/main/vless_lite.txt")
    val whitelistMonitoringEnabled by settingsManager.whitelistMonitoringEnabledFlow.collectAsState(false)

    LaunchedEffect(threadCount, timeoutMs) {
        networkService = NetworkService(threadCount, timeoutMs)
    }

    LaunchedEffect(whitelistMonitoringEnabled) {
        if (whitelistMonitoringEnabled) {
            WhitelistMonitorWorker.scheduleMonitoring(context)
        } else {
            WhitelistMonitorWorker.stopMonitoring(context)
        }
    }

    var loadedText by remember { mutableStateOf(TextFieldValue("")) }
    var bottomText by remember { mutableStateOf(TextFieldValue("")) }
    var totalCount by remember { mutableIntStateOf(0) }
    var goodCount by remember { mutableIntStateOf(0) }
    var badCount by remember { mutableIntStateOf(0) }
    var isProcessing by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        Toast.makeText(context, context.getString(R.string.developed_by), Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            TextField(
                value = loadedText,
                onValueChange = { newValue -> loadedText = newValue },
                label = { Text(stringResource(R.string.configs_label)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                maxLines = Int.MAX_VALUE
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = stringResource(R.string.all_label) + totalCount)
                Text(text = stringResource(R.string.good_label) + goodCount, color = Color.Green)
                Text(text = stringResource(R.string.bad_label) + badCount, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    Toast.makeText(context, context.getString(R.string.parsing_toast), Toast.LENGTH_SHORT).show()
                    coroutineScope.launch(Dispatchers.IO) {
                        try {
                            val url = URL(parsingUrl)
                            val content = url.readText()
                            val lines = parser.parseLines(content)
                            loadedText = TextFieldValue(content)
                            totalCount = lines.size
                            goodCount = 0
                            badCount = 0
                            bottomText = TextFieldValue("")
                        } catch (e: Exception) {
                            Toast.makeText(context, context.getString(R.string.failed_parse_toast), Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.load_button))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    Toast.makeText(context, context.getString(R.string.checking_toast), Toast.LENGTH_SHORT).show()
                    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val network = connectivityManager.activeNetwork
                    val capabilities = connectivityManager.getNetworkCapabilities(network)
                    if (capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true) {
                        Toast.makeText(context, context.getString(R.string.turn_off_wifi_toast), Toast.LENGTH_SHORT).show()
                    } else {
                        isProcessing = true
                        coroutineScope.launch {
                            val lines = parser.parseLines(loadedText.text)
                            checkLines(lines, parser, networkService).collect { (good, bad, goodLines) ->
                                goodCount = good
                                badCount = bad
                                bottomText = TextFieldValue(goodLines.joinToString("\n"))
                            }
                            isProcessing = false
                        }
                    }
                },
                enabled = loadedText.text.isNotEmpty() && !isProcessing,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isProcessing) stringResource(R.string.checking_button) else stringResource(R.string.check_button))
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = bottomText,
                onValueChange = {  },
                label = { Text(stringResource(R.string.valid_label)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                readOnly = false,
                maxLines = Int.MAX_VALUE
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText(context.getString(R.string.valid_label), bottomText.text)
                    clipboardManager.setPrimaryClip(clip)
                    Toast.makeText(context, context.getString(R.string.copied_clipboard_toast), Toast.LENGTH_SHORT).show()
                },
                enabled = bottomText.text.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.import_clipboard_button))
            }
        }
    }
}

fun checkLines(lines: List<String>, parser: Parser, networkService: NetworkService): Flow<Triple<Int, Int, List<String>>> = flow {
    var good = 0
    var bad = 0
    val goodLines = mutableListOf<String>()
    for (line in lines) {
        val ip = parser.extractIp(line)
        if (ip != null) {
            val reachable = networkService.isReachable(ip)
            if (reachable) {
                good++
                goodLines.add(line)
            } else {
                bad++
            }
        } else {
            bad++
        }
        emit(Triple(good, bad, goodLines.toList()))
    }
}
