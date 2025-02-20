package com.app7k.lounge.presentation.screens.bonus.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app7k.lounge.R
import com.app7k.lounge.ui.components.AppButton
import com.app7k.lounge.ui.components.AppText

@Composable
fun WelcomeFragment(onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            text = stringResource(R.string.welcome_bonus),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        AppText(
            modifier = Modifier.padding(horizontal = 20.dp),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.get_welcome_bonus),
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp
        )
        AppButton(
            text = stringResource(R.string.get_a_bonus),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
        ) {
            onClick()
        }
    }
}