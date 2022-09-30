package com.tripletres.platformscience.domain

import com.tripletres.platformscience.data.repo.DriverRepository
import com.tripletres.platformscience.data.repo.ShipmentRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Assign drivers to shipments use case test class.
 */
class AssignDriversToShipmentsUseCaseUnitTest {

    @RelaxedMockK
    lateinit var driverRepository: DriverRepository

    @RelaxedMockK
    lateinit var shipmentRepository: ShipmentRepository

    @RelaxedMockK
    lateinit var saveDriversAssigned: SaveDriversAssignedToShipmentsUseCase

    private var algorithmString = "GREEDY"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }


    @Test
    fun `When assigning drivers and shipments Then get all db stored data`() =
        runBlocking {
            //When assign driers to shipments
            val res = AssignDriversToShipmentsUseCase(
                driverRepository,
                shipmentRepository,
                saveDriversAssigned
            ).invoke(algorithmString)

            //Then get db data
            assertNotNull(res)
            coVerify { driverRepository.getDriversFromDB() }
            coVerify { shipmentRepository.getShipmentsFromDB() }
        }

    @Test
    fun `When assigned drivers and shipments Then call db storage for drivers`() =
        runBlocking {

            //When assign drivers to shiipments
            val res = AssignDriversToShipmentsUseCase(
                driverRepository,
                shipmentRepository,
                saveDriversAssigned
            ).invoke(algorithmString)

            //Then save drivers assigned
            assertNotNull(res)
            coVerify(atMost = 1) { saveDriversAssigned(any()) }
        }
}