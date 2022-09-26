package com.tripletres.platformscience.data.repo

import com.tripletres.platformscience.data.db.asDriverEntityList
import com.tripletres.platformscience.data.db.asShipmentEntityList
import com.tripletres.platformscience.data.db.driver.DriverEntity
import com.tripletres.platformscience.data.network.ShipmentDriverService
import javax.inject.Inject

class ShipmentDriverRepository @Inject constructor(
    private val api: ShipmentDriverService,
    private val shipmentRepository: ShipmentRepository,
    private val driverRepository: DriverRepository
) {

    suspend fun fetchShipmentsWithDrivers() {
        // Fetches the data json with shipments and drivers lists
        val shipmentsWithDrivers = api.getShipmentsWithDrivers()

        // Saves in local db shipments
        if (shipmentsWithDrivers.shipments.isNotEmpty()) {
            shipmentRepository.saveShipments(shipmentsWithDrivers.shipments.asShipmentEntityList())
        }

        // Then drivers too
        if (shipmentsWithDrivers.drivers.isNotEmpty()) {
            driverRepository.saveDrivers(shipmentsWithDrivers.drivers.asDriverEntityList())
        }
    }

    suspend fun getDrivers(): List<DriverEntity> = driverRepository.getDriversFromDB()
}