package com.nestor.spacex.di

import com.nestor.spacex.repository.dispatcher_provider.DefaultDispatcherProvider
import com.nestor.spacex.repository.dispatcher_provider.DispatcherProvider
import com.nestor.spacex.repository.launches.LaunchesRepository
import com.nestor.spacex.repository.launches.LaunchesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsLaunchesRepository(launchesRepositoryImpl: LaunchesRepositoryImpl): LaunchesRepository

    @Binds
    abstract fun bindsDispatcherProvider(dispatcherProvider: DefaultDispatcherProvider): DispatcherProvider
}