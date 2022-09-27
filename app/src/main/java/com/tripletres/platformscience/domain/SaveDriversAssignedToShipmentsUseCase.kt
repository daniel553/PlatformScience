package com.tripletres.platformscience.domain

import com.tripletres.platformscience.data.repo.DriverRepository
import com.tripletres.platformscience.domain.model.Driver
import com.tripletres.platformscience.domain.model.asDriverEntity
import javax.inject.Inject

class SaveDriversAssignedToShipmentsUseCase @Inject constructor(
    private val driverRepository: DriverRepository
) {
    suspend operator fun invoke(driversAssigned: List<Driver>) {
        driverRepository.saveDrivers(driversAssigned.map {  d ->
            d.asDriverEntity()
        })
    }
}
