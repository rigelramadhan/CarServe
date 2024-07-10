package one.reevdev.carserve.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.reevdev.carserve.core.data.utils.resourceprovider.AndroidResourceProvider
import one.reevdev.carserve.core.data.utils.resourceprovider.ResourceProvider

@Module
@InstallIn(SingletonComponent::class)
interface ResourceModule {

    @Binds
    fun provideResourceProvider(resourceProvider: AndroidResourceProvider): ResourceProvider
}