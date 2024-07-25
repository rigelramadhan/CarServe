package one.reevdev.carserve.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.reevdev.carserve.core.data.feature.auth.repository.AuthRepository
import one.reevdev.carserve.core.data.feature.auth.repository.AuthRepositoryImpl
import one.reevdev.carserve.core.data.feature.profile.repository.ProfileRepository
import one.reevdev.carserve.core.data.feature.profile.repository.ProfileRepositoryImpl
import one.reevdev.carserve.core.data.feature.service.repository.ServiceRepository
import one.reevdev.carserve.core.data.feature.service.repository.ServiceRepositoryImpl
import one.reevdev.carserve.core.data.feature.vehicle.repository.VehicleRepository
import one.reevdev.carserve.core.data.feature.vehicle.repository.VehicleRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideServiceRepository(repositoryImpl: ServiceRepositoryImpl): ServiceRepository

    @Singleton
    @Binds
    fun provideVehicleRepository(repositoryImpl: VehicleRepositoryImpl): VehicleRepository

    @Singleton
    @Binds
    fun provideAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    fun provideProfileRepository(repositoryImpl: ProfileRepositoryImpl): ProfileRepository
}