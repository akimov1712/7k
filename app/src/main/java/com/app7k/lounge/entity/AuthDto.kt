package com.app7k.lounge.entity

import kotlinx.serialization.SerialName

data class AuthDto(
    @SerialName("phone") val phone: String
)