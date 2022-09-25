package com.tripletres.platformscience.data.db.driver

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DriverDao {
    @Query("SELECT * FROM driver")
    suspend fun getAll(): List<DriverEntity>

    @Query("SELECT * FROM driver WHERE id = :id")
    suspend fun getById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(driverEntity: DriverEntity)

    @Query("DELETE FROM driver")
    suspend fun deleteAll()
}