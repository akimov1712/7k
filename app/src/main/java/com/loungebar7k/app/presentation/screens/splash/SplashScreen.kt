package com.loungebar7k.app.presentation.screens.splash

import android.widget.ProgressBar
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.loungebar7k.app.R
import com.loungebar7k.app.presentation.screens.auth.AuthScreen
import com.loungebar7k.app.ui.components.AppText
import com.loungebar7k.app.ui.theme.Colors
import com.loungebar7k.app.ui.theme.Fonts
import kotlinx.coroutines.delay

object SplashScreen: Screen {

    @Composable
    override fun Content() {
        SplashScreen()
    }

}

@Preview
@Composable
private fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Colors.RED, Colors.RED_BLACK))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val navigator = LocalNavigator.currentOrThrow
        var progress by remember { mutableStateOf(0.0f) }
        LaunchedEffect(Unit) {
            while (progress < 1f){
                progress += 0.01f
                delay(25)
            }
            delay(300)
            navigator.replaceAll(AuthScreen)
        }
        Box(Modifier
            .fillMaxWidth()
            .weight(1f), contentAlignment = Alignment.Center){
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = null
            )
        }
        ProgressBar(
            progress = progress,
            Modifier.padding(bottom = 100.dp)
        )
    }
}

@Composable
private fun ProgressBar(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Box(modifier.size(height = 30.dp, width = 200.dp)
        .background(Color.Black, CircleShape)
        .border(2.dp, Color.White, CircleShape)
        .clip(CircleShape)
    ){
        Box(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .background(Color(0xffCA041D), CircleShape)
                .border(2.dp, Color.White, CircleShape)
                .clipToBounds()
        )
        AppText(
            modifier = Modifier.align(Alignment.Center),
            text = "LOADING...",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}