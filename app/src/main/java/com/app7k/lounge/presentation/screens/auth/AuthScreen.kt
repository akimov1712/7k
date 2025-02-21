package com.app7k.lounge.presentation.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.app7k.lounge.R
import com.app7k.lounge.presentation.screens.tabs.TabsScreen
import com.app7k.lounge.presentation.screens.webView.WebViewScreen
import com.app7k.lounge.ui.components.AppButton
import com.app7k.lounge.ui.components.AppText
import com.app7k.lounge.ui.components.CodeField
import com.app7k.lounge.ui.theme.Fonts
import com.app7k.lounge.ui.utills.dropShadow
import com.app7k.lounge.utills.checkPhoneNumber
import com.app7k.lounge.utills.getNumberHint
import com.joelkanyi.jcomposecountrycodepicker.component.KomposeCountryCodePicker
import com.joelkanyi.jcomposecountrycodepicker.component.rememberKomposeCountryCodePickerState
import kotlinx.coroutines.delay

object AuthScreen: Screen{

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xff1A0409),
                            Color(0xff060005),
                        )
                    )
                )
                .padding(vertical = 40.dp, horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val navigator = LocalNavigator.currentOrThrow
            val context = LocalContext.current
            val viewModel = remember { AuthViewModel(context) }
            val state by viewModel.state.collectAsState()
            LaunchedEffect(state.errorMessage) {
                state.errorMessage?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    viewModel.changeErrorMessage(null)
                }
            }
            LaunchedEffect(state.openScreen) {
                state.openScreen?.let {
                    when(val screen = it){
                        AuthState.OpenScreen.NATIVE -> navigator.replaceAll(TabsScreen)
                        is AuthState.OpenScreen.WEBVIEW -> navigator.replaceAll(WebViewScreen(screen.url))
                    }
                }
            }
            when(state.screenState){
                AuthState.ScreenState.PHONE -> EnterPhoneContent(viewModel)
                AuthState.ScreenState.CODE -> ConfirmCodeContent(viewModel)
            }
        }
    }

}

@Composable
private fun ColumnScope.ConfirmCodeContent(viewModel: AuthViewModel) {
    Column (verticalArrangement = Arrangement.spacedBy(20.dp)){
        Box(Modifier
            .weight(1f)
            .padding(20.dp)) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .aspectRatio(1f)
                    .dropShadow(
                        CircleShape,
                        Color.Red.copy(0.25f),
                        offsetY = 0.dp,
                        offsetX = 0.dp,
                        blur = 30.dp
                    )
                    .padding(10.dp),
                painter = painterResource(R.drawable.icon),
                contentDescription = null
            )
        }
        Spacer(Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xff2B0F0D), RoundedCornerShape(12.dp))
                .border(2.dp, Color.White, RoundedCornerShape(12.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var code by rememberSaveable { mutableStateOf("") }
            var isValid by rememberSaveable { mutableStateOf(false) }
            AppText(
                text = stringResource(R.string.enter_code),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(20.dp))
            CodeField(code) { newValue, newIsValid ->
                code = newValue
                isValid = newIsValid
            }
            Spacer(Modifier.height(20.dp))
            AppButton(
                text = stringResource(R.string.registration),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enable = isValid
            ) {
                viewModel.confirm(code)
            }
            Spacer(Modifier.height(10.dp))
            AppText(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xff1B0A0A))
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { viewModel.cancel() }
                    .padding(vertical = 8.dp, horizontal = 24.dp),
                text = stringResource(R.string.cancel),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
            )
        }
        Spacer(Modifier.weight(1f))
    }
}


@Composable
private fun EnterPhoneContent(viewModel: AuthViewModel) {
    val viewModelState by viewModel.state.collectAsState()
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .aspectRatio(1f)
            .dropShadow(
                CircleShape,
                Color.Red.copy(0.25f),
                offsetY = 0.dp,
                offsetX = 0.dp,
                blur = 30.dp
            )
            .padding(10.dp),
        painter = painterResource(R.drawable.icon),
        contentDescription = null
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        AppText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.to_continue),
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(20.dp))
        var phoneNumber by rememberSaveable { mutableStateOf("") }
        val state = rememberKomposeCountryCodePickerState(
            showCountryCode = true,
            showCountryFlag = true,
        )
        val isValidNumber =
            checkPhoneNumber(phoneNumber, state.getFullPhoneNumber(), state.countryCode)
        KomposeCountryCodePicker(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = phoneNumber,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.White,
                errorTextColor = Color.White,
                errorPlaceholderColor = Color(0xff5C5C5C),
                disabledPlaceholderColor = Color(0xff5C5C5C),
                focusedPlaceholderColor = Color(0xff5C5C5C),
                unfocusedPlaceholderColor = Color(0xff5C5C5C),
                errorIndicatorColor = Color.White,
                focusedIndicatorColor = Color.White,
                disabledIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedContainerColor = Color(0xff060005),
                errorContainerColor = Color(0xff060005),
                disabledContainerColor = Color(0xff060005),
                unfocusedContainerColor = Color(0xff060005),
                cursorColor = Color.White
            ),
            placeholder = {
                AppText(
                    text = stringResource(getNumberHint(it)),
                    fontSize = 18.sp,
                    fontFamily = Fonts.APP_FONT,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xff5C5C5C)
                )
            },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = Fonts.APP_FONT,
                fontWeight = FontWeight.Medium
            ),
            onValueChange = {
                phoneNumber = it
                viewModel.changeNumberPhone(state.getCountryPhoneCode() + phoneNumber)
            },
        )
        Spacer(Modifier.height(10.dp))
        AppButton(
            text = stringResource(R.string.registration),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enable = isValidNumber || phoneNumber.length == 10 || !viewModelState.isLoading
        ) {
            viewModel.registration()
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AppText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.by_click_register),
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
        AppText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.privacy_policy),
            fontWeight = FontWeight.Medium,
            textDecoration = TextDecoration.Underline,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(10.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(vertical = 12.dp, horizontal = 20.dp)
        ) {
            AppText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.sms_conf_free),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

