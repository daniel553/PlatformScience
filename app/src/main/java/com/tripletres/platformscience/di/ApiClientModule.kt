package com.tripletres.platformscience.di

import com.tripletres.platformscience.data.network.ShipmentDriverApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Main module for dependency injection
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiClientModule {

    @Provides
    @Singleton
    fun provideShipmentDriverApiClient(retrofit: Retrofit): ShipmentDriverApiClient =
        retrofit.create(ShipmentDriverApiClient::class.java)

}