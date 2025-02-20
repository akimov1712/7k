package com.loungebar7k.app.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.loungebar7k.app.R
import com.loungebar7k.app.ui.components.AppText
import com.loungebar7k.app.ui.theme.Colors

object AuthScreen: Screen{

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Colors.RED_BLACK)
                .padding(vertical = 40.dp, horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = null
            )
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                AppText(
                    text = ""
                )
            }
        }
    }
}