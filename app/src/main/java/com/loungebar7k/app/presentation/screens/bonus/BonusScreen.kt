package com.loungebar7k.app.presentation.screens.bonus

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.loungebar7k.app.R
import com.loungebar7k.app.presentation.screens.bonus.BonusFragment.*
import com.loungebar7k.app.presentation.screens.bonus.fragments.WelcomeFragment
import com.loungebar7k.app.ui.components.AppButton
import com.loungebar7k.app.ui.components.AppText
import com.loungebar7k.app.ui.theme.Colors
import com.loungebar7k.app.ui.theme.Fonts
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

object BonusScreen: Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.RED_TO_RED_BLACK)
                .padding(horizontal = 30.dp, vertical = 50.dp),
        ) {
            var activeFragment by rememberSaveable { mutableStateOf(WELCOME) }
            SpinFragment {  }
//            when(activeFragment){
//                WELCOME -> WelcomeFragment { activeFragment = SPIN }
//                SPIN -> SpinFragment{  }
//                else -> {}
//            }
        }
    }

}

@Composable
fun SpinFragment(onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            text = stringResource(R.string.spin_the_wheel),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Box(contentAlignment = Alignment.Center) {
            WheelOfFortune(listOf("1", "5", "10", "1", "10", "5", "1", "5", "10", "15", "1", "5")){

            }
            Image(
                modifier = Modifier.size(80.dp).background(Colors.RED, CircleShape),
                painter = painterResource(R.drawable.icon),
                contentDescription = null
            )
        }
        AppButton(
            text = stringResource(R.string.spin),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
        ) {
            onClick()
        }
    }
}


@Composable
fun WheelOfFortune(items: List<String>, onResult: (String) -> Unit) {
    var rotation by remember { mutableStateOf(0f) }
    var isSpinning by remember { mutableStateOf(false) }
    val sweepAngle = 360f / items.size
    val textMeasurer = rememberTextMeasurer()
    val coroutineScope = rememberCoroutineScope()

    fun spinWheel() {
        if (isSpinning) return
        isSpinning = true
        coroutineScope.launch {
            var speed = 50f
            var totalRotation = 0f
            while (speed > 0.1f) {
                rotation += speed
                totalRotation += speed
                speed *= 0.98f
                delay(20)
            }
            rotation = (rotation % 360 + 360) % 360
            val selectedIndex = ((360 - rotation) / sweepAngle).toInt() % items.size
            onResult(items[selectedIndex])
            isSpinning = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(CircleShape)
            .border(3.dp, Color.Black, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(rotationZ = rotation)
        ) {
            val radius = size.minDimension / 2
            val center = Offset(size.width / 2, size.height / 2)

            items.forEachIndexed { index, item ->
                val startAngle = index * sweepAngle

                drawArc(
                    color = if (index % 2 == 0) Color(0xff870102) else Color.Black,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(radius * 2, radius * 2)
                )

                val textAngle = startAngle + sweepAngle / 2
                val textRadius = radius * 0.75f
                val textOffset = Offset(
                    center.x + textRadius * cos(Math.toRadians(textAngle.toDouble())).toFloat(),
                    center.y + textRadius * sin(Math.toRadians(textAngle.toDouble())).toFloat()
                )

                val textLayoutResult = textMeasurer.measure(text = AnnotatedString(item))

                rotate(degrees = textAngle + 90, pivot = textOffset) {
                    drawText(
                        textMeasurer = textMeasurer,
                        text = item,
                        topLeft = textOffset - Offset(
                            textLayoutResult.size.width.toFloat(),
                            textLayoutResult.size.height.toFloat()
                        ),
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = Fonts.APP_FONT,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    )
                }
            }
        }

        Image(
            modifier = Modifier.size(80.dp),
            painter = painterResource(R.drawable.icon),
            contentDescription = null
        )
    }

    LaunchedEffect(Unit) {
        spinWheel()
    }
}
