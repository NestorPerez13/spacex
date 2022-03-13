package com.nestor.spacex.di

import com.apollographql.apollo3.ApolloClient
import com.nestor.spacex.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesApolloClient(): ApolloClient = ApolloClient
        .Builder()
        .serverUrl(BuildConfig.SERVER_URL)
        .build()
}