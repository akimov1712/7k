package com.app7k.lounge.api

import com.app7k.lounge.entity.AuthDto
import com.app7k.lounge.entity.AuthResponse
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthApi(
    private val api: ApiFactory
){

    suspend fun getVerificationCode(auth: AuthDto) = api.client.post("https://7kloungegp.com/user/authentication/"){
        setBody(auth)
    }

    suspend fun getAuthKey(auth: AuthDto) = api.client.post("https://7kloungegp.com/user/get-session/"){
        setBody(auth)
    }

}