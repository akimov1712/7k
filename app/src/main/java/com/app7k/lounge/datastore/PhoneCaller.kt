package com.app7k.lounge.datastore

import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

suspend fun Settings.getPhone(): String? {
    val phone = this.data
        .map { it[AppSettings.KEY_PHONE] }
        .firstOrNull()
    return if (!phone.isNullOrEmpty()) phone else null
}

suspend fun Settings.savePhone(phone: String){
    this.edit {
        it[AppSettings.KEY_PHONE] = phone
    }
}