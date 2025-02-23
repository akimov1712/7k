package com.app7k.lounge.presentation.screens.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app7k.lounge.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(context: Context): ViewModel() {

    private val repository = AuthRepository(context)

    private val _state = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state.asStateFlow()

    private fun loadNumber() = viewModelScope.launch{
        val response = repository.loadFromCache()
        if (response == null){
            _state.value = state.value.copy(SplashState.Screen.AUTH)
            return@launch
        }
        if (response.sessionUrl.isNullOrEmpty() && response.session.isNullOrEmpty()){
            _state.value = state.value.copy(SplashState.Screen.NATIVE)
            return@launch
        }
        if (response.sessionUrl != null){
            _state.value = state.value.copy(SplashState.Screen.WEBVIEW(response.sessionUrl))
        }
    }

    init {
        loadNumber()
    }

}