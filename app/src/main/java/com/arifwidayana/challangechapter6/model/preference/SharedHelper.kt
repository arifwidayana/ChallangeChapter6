package com.arifwidayana.challangechapter6.model.preference

import android.content.Context
import android.content.SharedPreferences
import com.arifwidayana.challangechapter6.model.utils.Constant

class SharedHelper(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences(Constant.PREF, Context.MODE_PRIVATE)
    private val editor:SharedPreferences.Editor = sharedPref.edit()

    fun put(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, value: Boolean): Boolean {
        return sharedPref.getBoolean(key, value)
    }

    fun getString(key: String, value: String): String? {
        return sharedPref.getString(key, value)
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun clear() {
        editor.clear()
            .apply()
    }
}