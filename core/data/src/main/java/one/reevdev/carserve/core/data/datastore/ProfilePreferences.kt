package one.reevdev.carserve.core.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import one.reevdev.carserve.core.common.data.emptyString

class ProfilePreferences(private val dataStore: DataStore<Preferences>) {

    private val lastUsedName = stringPreferencesKey("last_used_name")
    private val lastUsedEmail = stringPreferencesKey("last_used_email")
    private val lastUsedPhoneNumber = stringPreferencesKey("last_used_phone_number")
    private val lastUsedAddress = stringPreferencesKey("last_used_address")

    fun getLastUserName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[lastUsedName] ?: emptyString()
        }
    }

    suspend fun setLastUserName(email: String) {
        dataStore.edit { preferences ->
            preferences[lastUsedName] = email
        }
    }

    fun getLastUserEmail(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[lastUsedEmail] ?: emptyString()
        }
    }

    suspend fun setLastUserEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[lastUsedEmail] = email
        }
    }

    fun getLastUserPhoneNumber(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[lastUsedPhoneNumber] ?: emptyString()
        }
    }

    suspend fun setLastUserPhoneNumber(email: String) {
        dataStore.edit { preferences ->
            preferences[lastUsedPhoneNumber] = email
        }
    }

    fun getLastUserAddress(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[lastUsedAddress] ?: emptyString()
        }
    }

    suspend fun setLastUserAddress(email: String) {
        dataStore.edit { preferences ->
            preferences[lastUsedAddress] = email
        }
    }

    suspend fun removeAllSavedData() {
        dataStore.edit { preferences ->
            preferences[lastUsedName] = emptyString()
            preferences[lastUsedEmail] = emptyString()
            preferences[lastUsedPhoneNumber] = emptyString()
            preferences[lastUsedAddress] = emptyString()
        }
    }
}
