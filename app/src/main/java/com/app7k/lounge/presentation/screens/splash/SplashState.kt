package com.app7k.lounge.presentation.screens.splash

import cafe.adriel.voyager.core.screen.Screen

data class SplashState(
    val openScreen: Screen? = null
){

    sealed interface Screen{

       data object AUTH: Screen
       data object NATIVE: Screen
       data class WEBVIEW(val url: String): Screen

    }

}