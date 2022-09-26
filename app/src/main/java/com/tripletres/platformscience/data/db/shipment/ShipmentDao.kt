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
    @Query("SELECT * FROM shipment_table")
    suspend fun getAll(): List<ShipmentEntity>

    @Query("SELECT * FROM shipment_table WHERE id = :id")
    suspend fun getById(id: Int): ShipmentEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shipmentEntity: ShipmentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(shipments: List<ShipmentEntity>)

    @Query("DELETE FROM shipment_table")
    suspend fun deleteAll()
}