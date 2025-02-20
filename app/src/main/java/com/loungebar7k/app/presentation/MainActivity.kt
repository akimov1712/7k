package com.loungebar7k.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.loungebar7k.app.presentation.screens.bonus.BonusScreen
import com.loungebar7k.app.presentation.screens.contact.ContactScreen
import com.loungebar7k.app.ui.theme.Colors

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(Colors.RED)
            Navigator(BonusScreen)
        }
    }

}

