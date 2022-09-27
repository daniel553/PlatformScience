package com.tripletres.platformscience.domain

import com.tripletres.platformscience.data.repo.DriverRepository
import com.tripletres.platformscience.data.repo.ShipmentDriverRepository
import com.tripletres.platformscience.data.repo.ShipmentRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Load drivers with shipments use case unit test class.
 */
class LoadDriversShipmentsUseCaseUnitTest {

    @RelaxedMockK
    lateinit var driversShipmentsRepository: ShipmentDriverRepository

    @RelaxedMockK
    lateinit var driverRepository: DriverRepository

    @RelaxedMockK
    lateinit var shipmentRepository: ShipmentRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        driversShipmentsRepository
    }

    @Test
    fun `When success load driver shipment Then call clear db for driver and shipment`() =
        runBlocking {
            //Given a success response from fetch
            coEvery { driversShipmentsRepository.fetchShipmentsWithDriversFromApi() } returns true

            //When load drivers shipment use case
            val res = LoadDriversShipmentsUseCase(
                driversShipmentsRepository,
                driverRepository,
                shipmentRepository
            ).invoke()

            //Then db is clear and success
            coVerify { driverRepository.clearDriversFromDB() }
            coVerify { shipmentRepository.clearShipmentsFromDB() }

            assertTrue(res)
        }

}