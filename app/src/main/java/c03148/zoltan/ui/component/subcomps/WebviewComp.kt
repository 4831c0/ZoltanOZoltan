package c03148.zoltan.ui.component.subcomps

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import c03148.zoltan.WebAppInterface

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebView(url: String, modifier: Modifier = Modifier) {

    AndroidView(
        modifier = modifier,
        factory = { context ->
            val webview = WebView(context).apply {
                webViewClient = WebViewClient()
                loadUrl(url)
                settings.javaScriptEnabled = true
            }
            webview.addJavascriptInterface(WebAppInterface(context), "API")

            webview
        }
    )
}