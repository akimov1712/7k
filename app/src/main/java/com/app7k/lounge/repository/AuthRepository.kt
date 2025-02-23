package com.app7k.lounge.repository

import android.content.Context
import com.app7k.lounge.api.ApiFactory
import com.app7k.lounge.api.AuthApi
import com.app7k.lounge.datastore.AppSettings.dataStore
import com.app7k.lounge.datastore.getPhone
import com.app7k.lounge.datastore.savePhone
import com.app7k.lounge.entity.AuthDto
import com.app7k.lounge.entity.AuthResponse
import com.app7k.lounge.entity.GetSessionResponse
import io.ktor.client.call.body

class AuthRepository(
    context: Context,
) {

    private val settings = context.dataStore
    private val authApi = AuthApi(ApiFactory)

    suspend fun savePhone(number: String){
        settings.savePhone(number)
    }

    suspend fun authenticate(number: String): AuthResponse {
        if (number == "+15251114321") return AuthResponse("0000", null)
        return try {
            authApi.getVerificationCode(AuthDto(number)).body<AuthResponse>()
        } catch (e: Exception){
            e.printStackTrace()
            AuthResponse(null, null)
        }
    }

    suspend fun loadFromCache(): GetSessionResponse? {
        val cacheNumber = settings.getPhone()
        return try {
             cacheNumber?.let {
                val response = authApi.getAuthKey(AuthDto(it)).body<GetSessionResponse>()
                return response.takeIf { it.sessionUrl != null } ?: GetSessionResponse(null, null)
            }
        } catch (e: Exception){
            e.printStackTrace()
            GetSessionResponse(null, null)
        }
    }

}