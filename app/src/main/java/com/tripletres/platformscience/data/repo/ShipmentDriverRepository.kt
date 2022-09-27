package com.tripletres.platformscience.data.repo

import com.tripletres.platformscience.data.db.asDriverEntityList
import com.tripletres.platformscience.data.db.asShipmentEntityList
import com.tripletres.platformscience.data.db.driver.DriverEntity
import com.tripletres.platformscience.data.db.shipment.ShipmentEntity
import com.tripletres.platformscience.data.model.ShipmentDriverResponse
import com.tripletres.platformscience.data.network.ShipmentDriverService
import com.tripletres.platformscience.domain.model.Driver
import javax.inject.Inject

class ShipmentDriverRepository @Inject constructor(
    private val api: ShipmentDriverService,
    private val shipmentRepository: ShipmentRepository,
    private val driverRepository: DriverRepository
) {

    /**
     * Fetches shipments with drivers from API
     * @return true when response data is not empty
     */
    suspend fun fetchShipmentsWithDriversFromApi(): Boolean {
        // Fetches the data json with shipments and drivers lists
        val shipmentsWithDrivers = api.getShipmentsWithDrivers()

        // Saves them to data base
        saveShipmentsWithDriversToDB(
            shipmentsWithDrivers.shipments.asShipmentEntityList(),
            shipmentsWithDrivers.drivers.asDriverEntityList()
        )

        return shipmentsWithDrivers.shipments.isNotEmpty() &&
                shipmentsWithDrivers.drivers.isNotEmpty()
    }

    /**
     * Save new shipments and drivers to db
     */
    suspend fun saveShipmentsWithDriversToDB(
        shipments: List<ShipmentEntity>,
        drivers: List<DriverEntity>
    ) {
        // Saves in local db shipments
        if (shipments.isNotEmpty()) {
            shipmentRepository.saveShipments(shipments)
        }

        // Then drivers too
        if (drivers.isNotEmpty()) {
            driverRepository.saveDrivers(drivers)
        }
    }

}