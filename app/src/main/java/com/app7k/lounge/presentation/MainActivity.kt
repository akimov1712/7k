package com.app7k.lounge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.app7k.lounge.presentation.screens.auth.AuthScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.app7k.lounge.presentation.screens.bonus.BonusScreen
import com.app7k.lounge.presentation.screens.tabs.TabsScreen
import com.app7k.lounge.ui.theme.Colors

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(Colors.RED)
            Navigator(AuthScreen)
        }
    }

}

