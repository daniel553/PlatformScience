package com.tripletres.platformscience.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tripletres.platformscience.data.db.driver.DriverDao
import com.tripletres.platformscience.data.db.driver.DriverEntity
import com.tripletres.platformscience.data.db.shipment.ShipmentDao
import com.tripletres.platformscience.data.db.shipment.ShipmentEntity

@Database(entities = [ShipmentEntity::class, DriverEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val DB_NAME = "platform_science.db"
    }

    abstract fun shipmentDao(): ShipmentDao
    abstract fun driverDao(): DriverDao
}