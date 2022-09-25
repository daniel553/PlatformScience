package com.tripletres.platformscience.data.db.driver

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DriverDao {
    @Query("SELECT * FROM driver_table")
    suspend fun getAll(): List<DriverEntity>

    @Query("SELECT * FROM driver_table WHERE id = :id")
    suspend fun getById(id: Int): DriverEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(driverEntity: DriverEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(driverEntity: List<DriverEntity>)

    @Query("DELETE FROM driver_table")
    suspend fun deleteAll()
}