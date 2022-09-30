package com.tripletres.platformscience.domain.algorithm

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Branch and bound algorithm unit test
 */
class BranchAndBoundAlgorithmUnitTest {

    @Test
    fun `Given assignation matrix When branch and bound Then list of drivers assigned is return`() {
        //Given branching and bound algorithm with mock data
        val algorithm = BranchAndBoundAlgorithm()
        val input = ssMockList()
        val minSS = 13f

        //When executing algorithm
        val matching = algorithm.getBestMatching(input = input)

        //Then verify the result is a list of inputted size and has no null values
        assert(matching.isNotEmpty())
        assertEquals(matching.size, input[0].size)
        matching.forEach {
            assertNotNull(it.ss)
            assertNotNull(it.shipment)
        }
        var ss = 0f
        matching.map { it.ss }.toList().forEach { ss += it ?: 0f }
        assertEquals(minSS, ss)
    }
}