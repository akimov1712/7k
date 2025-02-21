package com.app7k.lounge.entity

import kotlinx.serialization.SerialName

data class AuthResponse(
    @SerialName("auth_code") val authCode: String?,
    @SerialName("auth_url") val authUrl: String?,
)