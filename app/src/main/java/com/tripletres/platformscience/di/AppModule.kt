package com.tripletres.platformscience.di

import com.tripletres.platformscience.data.network.ShipmentApiClient
import com.tripletres.platformscience.data.repo.ShipmentRepository
import com.tripletres.platformscience.ui.main.MainActivityViewModel
import com.tripletres.platformscience.util.RetrofitUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

/**
 * Application module for dependency injection
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance() : Retrofit = RetrofitUtil.instance()

}