package one.reevdev.carserve.core.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.reevdev.carserve.core.domain.feature.auth.usecase.AuthInteractor
import one.reevdev.carserve.core.domain.feature.auth.usecase.AuthUseCase
import one.reevdev.carserve.core.domain.feature.service.usecase.ServiceInteractor
import one.reevdev.carserve.core.domain.feature.service.usecase.ServiceUseCase
import one.reevdev.carserve.core.domain.feature.vehicle.usecase.VehicleInteractor
import one.reevdev.carserve.core.domain.feature.vehicle.usecase.VehicleUseCase

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun provideServiceUseCase(interactor: ServiceInteractor): ServiceUseCase

    @Binds
    fun provideVehicleUseCase(interactor: VehicleInteractor): VehicleUseCase

    @Binds
    fun provideAuthUseCase(interactor: AuthInteractor): AuthUseCase
}