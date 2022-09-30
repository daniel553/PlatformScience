package com.tripletres.platformscience.data.repo

import com.tripletres.platformscience.data.db.driver.DriverDao
import com.tripletres.platformscience.data.db.driver.DriverEntity
import javax.inject.Inject

/**
 * Driver repository class
 */
class DriverRepository @Inject constructor(
    private val driverDao: DriverDao
) {

    suspend fun saveDrivers(drivers: List<DriverEntity>) = driverDao.insertAll(drivers)
    suspend fun getDriversFromDB(): List<DriverEntity> = driverDao.getAll() ?: emptyList()
    suspend fun clearDriversFromDB() = driverDao.deleteAll()
    suspend fun getDriverByIdFromDB(id: Long): DriverEntity = driverDao.getById(id)

}