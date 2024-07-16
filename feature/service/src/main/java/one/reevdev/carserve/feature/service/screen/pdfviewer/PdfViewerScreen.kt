package one.reevdev.carserve.feature.service.screen.pdfviewer

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import java.io.File

@Composable
fun PdfViewerScreen(
    modifier: Modifier = Modifier,
    pdfFilePath: String,
    context: Context = LocalContext.current
) {
    val webView = remember { WebView(context) }

    DisposableEffect(Unit) {
        onDispose {
            webView.removeAllViews()
            webView.destroy()
        }
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = {
            webView.apply {
                settings.run {
                    @SuppressLint("SetJavaScriptEnabled")
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    allowContentAccess = true
                    allowFileAccess = true
                }
            }
        }
    ) { view ->
        view.loadUrl("file://${File(pdfFilePath).absolutePath}")
    }
}