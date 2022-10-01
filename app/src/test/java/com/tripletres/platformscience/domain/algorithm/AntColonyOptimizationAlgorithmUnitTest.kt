package com.tripletres.platformscience.domain.algorithm

import org.junit.Assert
import org.junit.Test

/**
 * Ant colony optimization algorithm unit test class.
 */
class AntColonyOptimizationAlgorithmUnitTest {

    @Test
    fun `Given assignation matrix When aco algorithm executed Then list of drivers assigned is return`() {
        //Given branching and bound algorithm with mock data
        val algorithm = AntColonyOptimizationAlgorithm()
        val input = ssMockList()
        val minSS = 13f

        //When executing algorithm
        val matching = algorithm.getBestMatching(input = input)

        //Then verify the result is a list of inputted size and has no null values
        assert(matching.isNotEmpty())
        Assert.assertEquals(matching.size, input[0].size)
        matching.forEach {
            Assert.assertNotNull(it.ss)
            Assert.assertNotNull(it.shipment)
        }
        var ss = 0f
        matching.map { it.ss }.toList().forEach { ss += it ?: 0f }
        Assert.assertEquals(minSS, ss)
    }

    @Test
    fun `Given larger assignation matrix When aco algorithm executed Then list of drivers assigned is return`() {
        //Given branching and bound algorithm with mock data
        val algorithm = AntColonyOptimizationAlgorithm()
        val input = ssMockList(major = true)
        val minSSWithMarginError = 100f // Expected to be less than 8% as error margin (93)

        //When executing algorithm
        val matching = algorithm.getBestMatching(input = input)

        //Then verify the result is a list of inputted size and has no null values
        assert(matching.isNotEmpty())
        Assert.assertEquals(matching.size, input[0].size)
        matching.forEach {
            Assert.assertNotNull(it.ss)
            Assert.assertNotNull(it.shipment)
        }
        var ss = 0f
        matching.map { it.ss }.toList().forEach { ss += it ?: 0f }
        assert(minSSWithMarginError > ss)
    }
}