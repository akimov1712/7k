package com.app7k.lounge.presentation.screens.auth

import com.app7k.lounge.entity.AuthResponse
import com.app7k.lounge.presentation.screens.splash.SplashState

data class AuthState(
    val authResponse: AuthResponse? = null,
    val fullNumberPhone: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val openScreen: OpenScreen? = null,
    val screenState: ScreenState = ScreenState.PHONE,
){

    enum class ScreenState{
        PHONE, CODE
    }

    sealed interface OpenScreen{

       data object NATIVE: OpenScreen
       data class WEBVIEW(val url: String): OpenScreen

    }

}