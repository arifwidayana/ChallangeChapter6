package com.arifwidayana.challangechapter6.repositoy

import androidx.lifecycle.asLiveData
import com.arifwidayana.challangechapter6.model.preference.DatastorePreference
import com.arifwidayana.challangechapter6.model.preference.UserPreference
import kotlinx.coroutines.flow.Flow

class UserRepository(private val pref: DatastorePreference) {
    fun getSession() = pref.getUserSession().asLiveData()
    suspend fun saveSession(user: String, pass: String, status: Boolean) = pref.saveUserSession(user, pass, status)
    suspend fun deleteSession() = pref.deleteUserSession()
}