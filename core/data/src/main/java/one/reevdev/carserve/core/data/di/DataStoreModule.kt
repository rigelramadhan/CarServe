package one.reevdev.carserve.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import one.reevdev.carserve.core.data.datastore.AuthPreferences
import one.reevdev.carserve.core.data.datastore.ProfilePreferences
import javax.inject.Singleton

private val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

private val Context.profileDataStore: DataStore<Preferences> by preferencesDataStore(name = "profile")

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun provideAuthPreferences(@ApplicationContext context: Context): AuthPreferences {
        return AuthPreferences(context.authDataStore)
    }

    @Provides
    @Singleton
    fun provideProfilePreferences(@ApplicationContext context: Context): ProfilePreferences {
        return ProfilePreferences(context.profileDataStore)
    }
}