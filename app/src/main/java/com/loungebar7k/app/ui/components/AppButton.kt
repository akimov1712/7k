package com.loungebar7k.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loungebar7k.app.ui.utills.innerShadow

@Composable
fun AppButton(
    text: String,
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    onClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }

    val bgColor = when {
        pressed -> Brush.verticalGradient(listOf(Color(0xffB50016), Color(0xff810F1D)))
        else  -> Brush.verticalGradient(listOf(Color(0xffCF031C), Color(0xffA71023)))
    }

    val innerShadow = Modifier.innerShadow(
        shape = RoundedCornerShape(12.dp),
        color = Color.Black.copy(if (pressed) 0.4f else 0.25f),
        offsetY = if (pressed) 4.dp else -6.dp,
        blur = if (pressed) 4.dp else 0.dp,
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .alpha(if (enable) 1f else 0.5f)
            .background(bgColor)
            .then(innerShadow)
            .pointerInput(Unit) {
                if (enable){
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            when {
                                event.changes.any { it.pressed } -> { pressed = true }
                                event.changes.all { !it.pressed } -> { pressed = false }
                            }
                        }
                    }
                }

            }.clickable(
                remember { MutableInteractionSource() },
                null
            ){ onClick() },
        contentAlignment = Alignment.Center
    ) {
        AppText(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}