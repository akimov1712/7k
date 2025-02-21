package com.app7k.lounge.api

import com.app7k.lounge.entity.AuthDto
import com.app7k.lounge.entity.AuthResponse
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthApi(
    private val api: ApiFactory
){

    suspend fun getVerificationCode(auth: AuthDto) = api.client.post("https://7kloungegp.com/auth/get-verification-code/"){
        setBody(auth)
    }

    suspend fun getAuthKey(auth: AuthDto) = api.client.post("https://7kloungegp.com/auth/get-auth-key/"){
        setBody(auth)
    }

}