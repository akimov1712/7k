package com.app7k.lounge.utills

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toFormattedDate(): String {
    val dateFormat = SimpleDateFormat("dd MMM. yyyy", Locale.getDefault())
    return dateFormat.format(Date(this))
}

fun formatTime(hours: Int, minutes: Int): String {
    val formattedHours = hours.toString().padStart(2, '0')
    val formattedMinutes = minutes.toString().padStart(2, '0')
    return "$formattedHours:$formattedMinutes"
}