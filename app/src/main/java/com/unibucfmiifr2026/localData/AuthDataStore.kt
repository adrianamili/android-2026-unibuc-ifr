package com.unibucfmiifr2026.localData

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.unibucfmiifr2026.ApplicationController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth-data-store")


class AuthDataStore {
    companion object {
        private val ARG_TOKEN = stringPreferencesKey("token")
    }

    val token : Flow<String?> = ApplicationController.instance.applicationContext.dataStore.data.map {
        it[ARG_TOKEN]
    }



    suspend fun saveToken(token: String) {
        ApplicationController.instance.applicationContext.dataStore.edit { preferences ->
            preferences[ARG_TOKEN] = token

        }
    }

    suspend fun removeToken() {
        ApplicationController.instance.applicationContext.dataStore.edit { preferences ->
            preferences.remove(ARG_TOKEN)
        }

    }


}