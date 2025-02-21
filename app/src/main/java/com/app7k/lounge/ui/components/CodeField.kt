package com.app7k.lounge.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app7k.lounge.ui.theme.Fonts
import com.composeuisuite.ohteepee.OhTeePeeInput
import com.composeuisuite.ohteepee.configuration.OhTeePeeCellConfiguration
import com.composeuisuite.ohteepee.configuration.OhTeePeeConfigurations

@Composable
fun CodeField(code: String, onChangeValue: (String, Boolean) -> Unit) {

    val defaultCellConfig = OhTeePeeCellConfiguration(
        borderColor = Color.Transparent,
        borderWidth = 0.dp,
        shape = RoundedCornerShape(4.dp),
        textStyle = TextStyle(
            color = Color.White,
            fontFamily = Fonts.APP_FONT,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        placeHolderTextStyle = TextStyle(
            color = Color.White,
            fontFamily = Fonts.APP_FONT,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        backgroundColor = Color(0xff2B0F0D)
    )

    OhTeePeeInput(
        value = code,
        onValueChange = { newValue, isValid ->
            onChangeValue(newValue, isValid)
        },
        configurations = OhTeePeeConfigurations(
            cellsCount = 4,
            emptyCellConfig = defaultCellConfig,
            cellModifier = Modifier.size(48.dp),
            elevation = 0.dp,
            cursorColor = Color.White,
            obscureText = "",
            placeHolder = "—",
            activeCellConfig = defaultCellConfig.copy(
                textStyle = defaultCellConfig.textStyle.copy(
                    color = Color.White
                )
            ),
            errorCellConfig = defaultCellConfig,
            filledCellConfig = defaultCellConfig,
            enableBottomLine = false,
        ),
    )
}