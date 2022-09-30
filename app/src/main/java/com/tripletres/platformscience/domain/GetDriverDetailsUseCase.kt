package com.tripletres.platformscience.domain

import com.tripletres.platformscience.data.repo.DriverRepository
import com.tripletres.platformscience.domain.model.Driver
import com.tripletres.platformscience.domain.model.asDriver
import javax.inject.Inject

/**
 * Use case for stored driver details
 */
class GetDriverDetailsUseCase @Inject constructor(
    private val driverRepository: DriverRepository
) {

    suspend operator fun invoke(id: Long): Driver {
        return driverRepository.getDriverByIdFromDB(id).asDriver()
    }
}