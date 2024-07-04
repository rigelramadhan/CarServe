package one.reevdev.carserve.core.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.reevdev.carserve.core.domain.usecase.service.ServiceInteractor
import one.reevdev.carserve.core.domain.usecase.service.ServiceUseCase

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun provideServiceUseCase(interactor: ServiceInteractor): ServiceUseCase
}