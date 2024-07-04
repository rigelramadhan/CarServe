package one.reevdev.carserve.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.reevdev.carserve.core.data.repository.service.ServiceRepository
import one.reevdev.carserve.core.data.repository.service.ServiceRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideServiceRepository(repositoryImpl: ServiceRepositoryImpl): ServiceRepository
}