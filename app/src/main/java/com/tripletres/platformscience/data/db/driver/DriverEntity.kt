package com.tripletres.platformscience.data.db.driver

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "driver_table")
data class DriverEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "driver_id")
    val id: Long = 0L,
    @ColumnInfo(name = "name") val name: String,
    @Embedded val assignation: DriverAssignationEntity?
)
