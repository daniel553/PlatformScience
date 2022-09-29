package com.tripletres.platformscience.domain

import com.tripletres.platformscience.data.db.driver.DriverEntity
import com.tripletres.platformscience.data.repo.DriverRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAssignedDriversToShipmentsUseCaseUnitTest {

    @RelaxedMockK
    private lateinit var driverRepository: DriverRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `When getting drivers Then driver repository to get all db stored data is called`() =
        runBlocking {
            val drivers = listOf(DriverEntity(1, "a", null))
            coEvery { driverRepository.getDriversFromDB() } returns drivers

            val res = GetAssignedDriversToShipmentsUseCase(driverRepository).invoke()

            assertNotNull(res)

            coVerify { driverRepository.getDriversFromDB() }
        }
}