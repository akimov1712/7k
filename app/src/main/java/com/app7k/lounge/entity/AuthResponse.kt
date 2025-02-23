package com.app7k.lounge.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    @SerialName("verification_code") val authCode: String? = null,
    @SerialName("verification_url") val authUrl: String? = null,
)