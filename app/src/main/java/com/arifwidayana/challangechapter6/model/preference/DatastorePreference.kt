package com.arifwidayana.challangechapter6.model.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.arifwidayana.challangechapter6.model.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastorePreference(context: Context) {
    companion object {
        private val USERNAME = stringPreferencesKey(Constant.USERNAME)
        private val PASSWORD = stringPreferencesKey(Constant.PASSWORD)
        private val LOGIN = booleanPreferencesKey(Constant.LOGIN)
        private val ON_BOARDING = booleanPreferencesKey(Constant.FINISHED)
    }

    private val Context.mDataStore: DataStore<Preferences> by preferencesDataStore(Constant.PREF)
    private val dataStore: DataStore<Preferences> = context.mDataStore

    fun getUserSession(): Flow<UserPreference> = dataStore.data.map { pref ->
        UserPreference(
            pref[USERNAME]?: "",
            pref[PASSWORD]?: "",
            pref[LOGIN]?: false,
            pref[ON_BOARDING]?: false
        )
    }

    suspend fun saveUserSession(email: String, password: String, login: Boolean){
        dataStore.edit { pref ->
            pref[USERNAME] = email
            pref[PASSWORD] = password
            pref[LOGIN] = login
        }
    }

    suspend fun saveOnBoardSession(onBoard: Boolean) {
        dataStore.edit {
            it[ON_BOARDING] = onBoard
        }
    }

    suspend fun deleteUserSession() {
        dataStore.edit {
            it.clear()
        }
    }
}