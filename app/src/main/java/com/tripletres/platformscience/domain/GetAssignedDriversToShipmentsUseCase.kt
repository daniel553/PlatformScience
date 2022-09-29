package com.tripletres.platformscience.domain

import com.tripletres.platformscience.data.repo.DriverRepository
import com.tripletres.platformscience.domain.model.Driver
import com.tripletres.platformscience.domain.model.asDriverList
import javax.inject.Inject

/**
 * Gets stored drivers assigned to shipments use case class
 */
class GetAssignedDriversToShipmentsUseCase @Inject constructor(
    val driverRepository: DriverRepository
) {
    suspend operator fun invoke(): List<Driver> {
        return driverRepository.getDriversFromDB().asDriverList()
    }
}