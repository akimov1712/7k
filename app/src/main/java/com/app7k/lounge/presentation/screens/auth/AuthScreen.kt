package com.app7k.lounge.presentation.screens.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.app7k.lounge.R
import com.app7k.lounge.ui.components.AppButton
import com.app7k.lounge.ui.components.AppText
import com.app7k.lounge.ui.theme.Colors
import com.app7k.lounge.ui.theme.Fonts
import com.app7k.lounge.ui.utills.dropShadow
import com.togitech.ccp.component.TogiCountryCodePicker
import java.time.format.TextStyle

object AuthScreen: Screen{

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(
                    Brush.verticalGradient(listOf(
                        Color(0xff1A0409),
                        Color(0xff060005),
                    ))
                )
                .padding(vertical = 40.dp, horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .aspectRatio(1f)
                    .dropShadow(CircleShape, Color.Red.copy(0.25f), offsetY = 0.dp, offsetX = 0.dp, blur = 30.dp)
                    .padding(10.dp),
                painter = painterResource(R.drawable.icon),
                contentDescription = null
            )
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
            ) {
                AppText(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.to_continue),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(20.dp))
                var fullPhoneNumber: String by rememberSaveable { mutableStateOf("") }
                var isNumberValid: Boolean by rememberSaveable { mutableStateOf(false) }
                TogiCountryCodePicker(
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    clearIcon = null,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        backgroundColor = Color(0xff060005),
                        placeholderColor = Color(0xff5C5C5C),
                        errorIndicatorColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        disabledIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                    ),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = Fonts.APP_FONT,
                        fontWeight = FontWeight.Medium
                    ),
                    onValueChange = { (code, phone), isValid ->
                        fullPhoneNumber = code + phone
                        isNumberValid = isValid
                    },
                )
                Spacer(Modifier.height(10.dp))
                AppButton(
                    text = stringResource(R.string.registration),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    enable = isNumberValid
                ) {

                }
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        }
    }
}
