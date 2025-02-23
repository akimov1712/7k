package com.app7k.lounge.presentation.screens.webView

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.kevinnzou.web.LoadingState
import com.kevinnzou.web.WebView
import com.kevinnzou.web.rememberWebViewNavigator
import com.kevinnzou.web.rememberWebViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WebViewScreen(private val url: String): Screen {

    @Composable
    override fun Content() {
        ScreenContent(url)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun ScreenContent(url: String) {
    var isError by remember { mutableStateOf(false) }
    val state = rememberWebViewState(url)
    val navigator = rememberWebViewNavigator()
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        state.lastLoadedUrl?.let { navigator.loadUrl(it) }
        refreshing = false
    }

    LaunchedEffect(state.errorsForCurrentRequest.lastOrNull()) {
        isError = state.errorsForCurrentRequest.isNotEmpty()
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Colors.RED_TO_RED_BLACK)
    ) {
        if (isError) {
            ErrorScreen(onRetry = {
                state.lastLoadedUrl?.let(navigator::loadUrl)
                isError = false
            })
        } else {
            if (state.loadingState is LoadingState.Loading) {
                LoadingIndicator()
            }
            Box(modifier = Modifier.fillMaxSize()) {
                PullToRefreshBox(
                    modifier = Modifier.fillMaxSize(),
                    isRefreshing = refreshing,
                    onRefresh = { refresh() }
                ) {
                    WebView(
                        modifier = Modifier.fillMaxSize(),
                        state = state,
                        onCreated = {
                            it.settings.javaScriptEnabled = true
                            it.settings.setSupportZoom(true)
                        },
                        navigator = navigator
                    )
                }
            }
        }
    }
}

@Composable
private fun ErrorScreen(onRetry: () -> Unit) {
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
                text = "Повторить",
                onClick = onRetry
            )
        }
    }
}

@Composable
private fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}
