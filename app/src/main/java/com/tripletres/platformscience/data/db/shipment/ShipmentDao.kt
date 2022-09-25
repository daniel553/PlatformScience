package com.tripletres.platformscience.data.db.shipment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data access object for shipments, defines the CRUD operations for shipments
 */
@Dao
interface ShipmentDao {
    @Query("SELECT * FROM shipment")
    suspend fun getAll(): List<ShipmentEntity>

    @Query("SELECT * FROM shipment WHERE id = :id")
    suspend fun getById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shipmentEntity: ShipmentEntity)

    @Query("DELETE FROM shipment")
    suspend fun deleteAll()
}