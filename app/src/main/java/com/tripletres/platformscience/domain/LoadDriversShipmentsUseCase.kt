package com.tripletres.platformscience.domain

import com.tripletres.platformscience.data.repo.DriverRepository
import com.tripletres.platformscience.data.repo.ShipmentDriverRepository
import com.tripletres.platformscience.data.repo.ShipmentRepository
import javax.inject.Inject

/**
 * Loads drivers and shipments from repository that works as a fetch from repo
 */
class LoadDriversShipmentsUseCase @Inject constructor(
    val driversShipmentsRepository: ShipmentDriverRepository,
    val driverRepository: DriverRepository,
    val shipmentRepository: ShipmentRepository
) {
    suspend operator fun invoke(useCached: Boolean?): Boolean {
        val cached = driverRepository.getDriversFromDB()

        return if (cached.isNotEmpty() && useCached == true) {
            //Do not fetch, just return an ok
            true
        } else {
            //Clear db
            driverRepository.clearDriversFromDB()
            shipmentRepository.clearShipmentsFromDB()

            //Fetch
            driversShipmentsRepository.fetchShipmentsWithDriversFromApi()
        }
    }
}