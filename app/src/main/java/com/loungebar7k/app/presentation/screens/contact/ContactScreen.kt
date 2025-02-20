package com.loungebar7k.app.presentation.screens.contact

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.loungebar7k.app.R
import com.loungebar7k.app.presentation.screens.reservation.ReservationEntity
import com.loungebar7k.app.ui.components.AppButton
import com.loungebar7k.app.ui.components.AppText
import com.loungebar7k.app.ui.components.AppTextField
import com.loungebar7k.app.ui.theme.Colors

object ContactScreen: Screen {


    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.RED_TO_RED_BLACK)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var name by remember{ mutableStateOf("") }
            var email by remember{ mutableStateOf("") }
            var message by remember{ mutableStateOf("") }
            AppText(
                text = stringResource(R.string.contact_us),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Spacer(Modifier.height(80.dp))
            AppText(
                text = stringResource(R.string.any_question),
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp
            )
            Spacer(Modifier.height(20.dp))
            AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            placeholder = stringResource(R.string.name),
            onValueChange = { name = it }
            )
            Spacer(Modifier.height(10.dp))
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                placeholder = stringResource(R.string.email),
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(Modifier.height(10.dp))
            AppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                value = message,
                alignment = Alignment.TopStart,
                singleLine = false,
                placeholder = stringResource(R.string.type_message),
                onValueChange = { message = it },
            )
            Spacer(Modifier.height(20.dp))
            val context = LocalContext.current
            val messageToast = stringResource(R.string.message_is_sent)
            AppButton(
                text = "Send",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enable = listOf(name, email, message).none { it.trim().isBlank() }
            ) {
                name = ""
                email = ""
                message = ""
                Toast.makeText(context, messageToast, Toast.LENGTH_SHORT).show()
            }
        }
    }

}