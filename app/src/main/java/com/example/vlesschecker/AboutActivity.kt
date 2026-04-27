package com.example.vlesschecker

import androidx.compose.ui.graphics.toArgb
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.vlesschecker.ui.theme.VlessCheckerTheme
import io.noties.markwon.Markwon

@OptIn(ExperimentalMaterial3Api::class)
class AboutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            VlessCheckerTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("About") },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        )
                    }
                ) { paddingValues ->
                    AboutScreen(Modifier.padding(paddingValues))
                }
            }
        }
    }
}
@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val colorScheme = MaterialTheme.colorScheme

    val markdown = remember {
        context.resources.openRawResource(R.raw.readme)
            .bufferedReader()
            .use { it.readText() }
    }

    AndroidView(
        modifier = modifier,
        factory = {
            val textView = TextView(it).apply {
                setBackgroundColor(android.graphics.Color.TRANSPARENT)
                movementMethod = android.text.method.LinkMovementMethod.getInstance()
            }

            val markwon = Markwon.create(it)
            markwon.setMarkdown(textView, markdown)

            textView
        },
        update = { textView ->
            textView.setTextColor(colorScheme.onBackground.toArgb())
            textView.setLinkTextColor(colorScheme.primary.toArgb())
        }
    )
}