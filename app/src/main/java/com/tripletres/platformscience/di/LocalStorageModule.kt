package com.tripletres.platformscience.di

import com.tripletres.platformscience.data.db.AppDatabase
import com.tripletres.platformscience.data.db.driver.DriverDao
import com.tripletres.platformscience.data.db.shipment.ShipmentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Local database storage module for dependency injection
 */
@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {

    @Provides
    @Singleton
    fun provideShipmentDao(db: AppDatabase): ShipmentDao = db.shipmentDao()

    @Provides
    @Singleton
    fun provideDriverDao(db: AppDatabase): DriverDao = db.driverDao()

}