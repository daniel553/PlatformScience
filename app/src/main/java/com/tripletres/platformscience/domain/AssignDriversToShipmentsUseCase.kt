package com.tripletres.platformscience.domain

import com.tripletres.platformscience.data.repo.DriverRepository
import com.tripletres.platformscience.data.repo.ShipmentRepository
import com.tripletres.platformscience.domain.algorithm.AssignationAlgorithmType
import com.tripletres.platformscience.domain.algorithm.BranchAndBoundAlgorithm
import com.tripletres.platformscience.domain.algorithm.GreedyAssignationAlgorithm
import com.tripletres.platformscience.domain.algorithm.IAssignationAlgorithm
import com.tripletres.platformscience.domain.model.*
import javax.inject.Inject

/**
 * Assigns one single driver to a single shipment address, storages in db
 */
class AssignDriversToShipmentsUseCase @Inject constructor(
    private val driverRepository: DriverRepository,
    private val shipmentRepository: ShipmentRepository,
    private val saveDriversAssigned: SaveDriversAssignedToShipmentsUseCase,
) {

    // Can be changed for your favorite algorithm
    private val defaultAlgorithm = AssignationAlgorithmType.BRANCH.name

    suspend operator fun invoke(algorithm: String?): List<Driver> {
        val drivers = driverRepository.getDriversFromDB().asDriverList()
        val shipments = shipmentRepository.getShipmentsFromDB().asShipmentList()

        val driverAssigned = assignDriversToShipment(drivers, shipments, algorithm)
        //Save in assigned drivers
        saveDriversAssigned(driverAssigned)
        return driverAssigned
    }

    /**
     * Perform assignation of shipments to drivers
     */
    private fun assignDriversToShipment(
        drivers: List<Driver>,
        shipments: List<Shipment>,
        algorithm: String?,
    ): List<Driver> {
        return ShipmentDriverAssignation(defineAlgorithm(algorithm)).execute(drivers, shipments)
    }

    /**
     * Gets assignation algorithm from type or default
     */
    private fun defineAlgorithm(algorithm: String?): IAssignationAlgorithm {
        val type = algorithm ?: defaultAlgorithm
        return when (type.toAssignationAlgorithmType()) {
            AssignationAlgorithmType.GREEDY -> GreedyAssignationAlgorithm()
            AssignationAlgorithmType.BRANCH -> BranchAndBoundAlgorithm()
        }
    }

}