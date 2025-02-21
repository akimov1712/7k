package com.app7k.lounge.presentation.screens.webView

import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.currentOrThrow
import com.app7k.lounge.presentation.screens.splash.SplashState
import com.app7k.lounge.ui.components.AppButton
import com.app7k.lounge.ui.components.AppText
import com.app7k.lounge.ui.theme.Colors

class WebViewScreen(private val url: String): Screen {

    @Composable
    override fun Content() {
        ScreenContent(url)
    }

}

@Composable
private fun ScreenContent(url: String) {
    var isError by remember { mutableStateOf(false) }
    var lastLoadedUrl by remember { mutableStateOf(url) }
    val activity = LocalActivity.currentOrThrow
    var webView: WebView? = null

    BackHandler {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else{
            activity.onBackPressed()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (isError) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .background(Colors.RED_TO_RED_BLACK)
                        .padding(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AppText(
                        text = "Произошла ошибка при загрузке",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    AppButton(
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        text = "Повторить"
                    ) {
                        isError = false
                        webView?.loadUrl(lastLoadedUrl)
                    }
                }
            }
        } else {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webView = this
                        settings.javaScriptEnabled = true
                        webViewClient = object : WebViewClient() {

                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                url?.let { lastLoadedUrl = it }
                            }

                            override fun onReceivedError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                error: WebResourceError?
                            ) {
                                val host = request?.url?.host ?: ""
                                println("Произошла ошибка на $host")
                                if (url.contains(host)){
                                    isError = true
                                }
                            }
                        }
                        loadUrl(lastLoadedUrl)
                    }
                },
                update = { it.loadUrl(lastLoadedUrl) }
            )
        }
    }
}