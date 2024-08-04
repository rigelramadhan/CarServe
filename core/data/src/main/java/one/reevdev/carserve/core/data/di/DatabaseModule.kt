package one.reevdev.carserve.core.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import one.reevdev.carserve.core.data.feature.profile.datasource.local.database.CustomerDatabase
import one.reevdev.carserve.core.data.feature.service.datasource.local.database.ServiceAnalysisDatabase
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.database.VehicleDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideVehicleDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, VehicleDatabase::class.java, "vehicle.db")
            .build()

    @Provides
    @Singleton
    fun provideVehicleDao(database: VehicleDatabase) = database.dao()

    @Provides
    @Singleton
    fun provideServiceAnalysisDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ServiceAnalysisDatabase::class.java, "analysis.db")
            .build()

    @Provides
    @Singleton
    fun provideServiceAnalysisDao(database: ServiceAnalysisDatabase) = database.dao()

    @Provides
    @Singleton
    fun provideCustomerDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CustomerDatabase::class.java, "customer.db")
            .build()

    @Provides
    @Singleton
    fun provideCustomerDao(database: CustomerDatabase) = database.dao()
}