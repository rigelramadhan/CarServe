package one.reevdev.carserve.core.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import one.reevdev.carserve.core.common.data.emptyString

class AuthPreferences(private val dataStore: DataStore<Preferences>) {

    private val keyUserEmail = stringPreferencesKey("user_email")

    fun getUserEmail(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[keyUserEmail] ?: emptyString()
        }
    }

    suspend fun setUserEmail(email: String) {
        dataStore.edit { auth ->
            auth[keyUserEmail] = email
        }
    }

    suspend fun logout() {
        dataStore.edit { auth ->
            auth[keyUserEmail] = emptyString()
        }
    }
}