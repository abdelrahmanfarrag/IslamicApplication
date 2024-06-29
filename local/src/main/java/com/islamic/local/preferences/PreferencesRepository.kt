package com.islamic.local.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : IPreferencesRepository {
    override fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
}