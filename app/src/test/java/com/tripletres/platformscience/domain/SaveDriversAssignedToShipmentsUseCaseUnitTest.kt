package com.tripletres.platformscience.domain

import com.tripletres.platformscience.data.repo.DriverRepository
import com.tripletres.platformscience.domain.model.Driver
import com.tripletres.platformscience.domain.model.Shipment
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Save drivers assigned to shipments use case
 */
class SaveDriversAssignedToShipmentsUseCaseUnitTest {

    @RelaxedMockK
    lateinit var driverRepository: DriverRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Given driver assigned When executed load driver shipment Then save in db driver with assigned shipment`() =
        runBlocking {
            //Given
            val driversAssigned = listOf(
                Driver(1, "a", Shipment(1, "s"), 1.0f),
                Driver(2, "a2", Shipment(2, "s2"), 1.0f)
            )
            //When save drivers assigned to shipment
            SaveDriversAssignedToShipmentsUseCase(driverRepository).invoke(
                driversAssigned = driversAssigned
            )

            //Then save it in DB
            coVerify { driverRepository.saveDrivers(any()) }
        }
}