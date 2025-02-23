package com.app7k.lounge.presentation.screens.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app7k.lounge.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(context: Context): ViewModel() {

    private val repository = AuthRepository(context)

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun confirm(code: String) = viewModelScope.launch{
        if (code == state.value.authResponse?.authCode){
            _state.value = state.value.copy(openScreen = AuthState.OpenScreen.NATIVE)
            repository.savePhone(state.value.fullNumberPhone)
        } else {
            changeErrorMessage("Код не совпадает")
        }
    }

    fun changeErrorMessage(str: String?){
        _state.value = state.value.copy(errorMessage = str)
    }

    fun cancel(){
        _state.value = AuthState()
    }

    fun changeNumberPhone(number: String){
        _state.value = state.value.copy(fullNumberPhone = number)
    }

    fun registration() = viewModelScope.launch {
        _state.value = state.value.copy(isLoading = true)
        val response = repository.authenticate(_state.value.fullNumberPhone)
        if (response.authCode != null) {
            _state.value = state.value.copy(authResponse = response, screenState = AuthState.ScreenState.CODE)
        } else {
            val authUrl = response.authUrl
            if (authUrl == null){
                _state.value = state.value.copy(openScreen = AuthState.OpenScreen.NATIVE)
            } else {
                _state.value = state.value.copy(openScreen = AuthState.OpenScreen.WEBVIEW(authUrl))
                repository.savePhone(state.value.fullNumberPhone)
            }
        }
    }

}