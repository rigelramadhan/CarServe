package one.reevdev.carserve.core.data.di

import com.github.theapache64.retrosheet.RetrosheetInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import one.reevdev.carserve.core.data.datasource.remote.RemoteConstants
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(retrosheetInterceptor: RetrosheetInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(retrosheetInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RemoteConstants.SHEET_BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideRetrosheetInterceptor(): RetrosheetInterceptor {
        return RetrosheetInterceptor.Builder()
            .addSheet("Services", "no", "service", "estimated_price")
            .build()
    }
}