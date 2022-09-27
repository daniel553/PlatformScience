package com.tripletres.platformscience.domain

import com.tripletres.platformscience.data.repo.DriverRepository
import com.tripletres.platformscience.data.repo.ShipmentDriverRepository
import com.tripletres.platformscience.data.repo.ShipmentRepository
import com.tripletres.platformscience.domain.model.Driver
import javax.inject.Inject

/**
 * Loads drivers and shipments from repository that works as a fetch from repo
 */
class LoadDriversShipmentsUseCase @Inject constructor(
    val driversShipmentsRepository: ShipmentDriverRepository,
    val driverRepository: DriverRepository,
    val shipmentRepository: ShipmentRepository
) {
    suspend operator fun invoke(): Boolean {
        //Clear db
        driverRepository.clearDriversFromDB()
        shipmentRepository.clearShipmentsFromDB()

        //Fetch
        return driversShipmentsRepository.fetchShipmentsWithDriversFromApi()
    }
}