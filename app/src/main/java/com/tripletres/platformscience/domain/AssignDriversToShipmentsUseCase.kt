package com.tripletres.platformscience.domain

import com.tripletres.platformscience.data.repo.DriverRepository
import com.tripletres.platformscience.data.repo.ShipmentRepository
import com.tripletres.platformscience.domain.algorithm.GreedyAssignationAlgorithm
import com.tripletres.platformscience.domain.model.Driver
import com.tripletres.platformscience.domain.model.Shipment
import com.tripletres.platformscience.domain.model.asDriverList
import com.tripletres.platformscience.domain.model.asShipmentList
import javax.inject.Inject

/**
 * Assigns one single driver to a single shipment address, storages in db
 */
class AssignDriversToShipmentsUseCase @Inject constructor(
    private val driverRepository: DriverRepository,
    private val shipmentRepository: ShipmentRepository,
    private val saveDriversAssigned: SaveDriversAssignedToShipmentsUseCase,
) {

    suspend operator fun invoke(): List<Driver> {
        val drivers = driverRepository.getDriversFromDB().asDriverList()
        val shipments = shipmentRepository.getShipmentsFromDB().asShipmentList()

        val driverAssigned = assignDriversToShipment(drivers, shipments)
        //Save in assigned drivers
        saveDriversAssigned(driverAssigned)
        return driverAssigned
    }

    /**
     * Perform assignation of shipments to drivers
     */
    private fun assignDriversToShipment(drivers: List<Driver>, shipments: List<Shipment>): List<Driver> {
        return ShipmentDriverAssignation(GreedyAssignationAlgorithm()).execute(drivers, shipments)
    }

}