package one.reevdev.carserve.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.reevdev.carserve.core.data.datasource.remote.sheet.SheetApi
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideApi(retrofit: Retrofit): SheetApi {
        return retrofit.create(SheetApi::class.java)
    }
}