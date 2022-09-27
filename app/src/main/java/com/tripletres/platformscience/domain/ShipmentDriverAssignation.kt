package com.tripletres.platformscience.domain

import com.tripletres.platformscience.domain.algorithm.IAssignationAlgorithm
import com.tripletres.platformscience.domain.model.Driver
import com.tripletres.platformscience.domain.model.Shipment
import com.tripletres.platformscience.util.commonFactors
import com.tripletres.platformscience.util.isEven

/**
 * Shipment driver assignation class, allows to perform driver assignation
 * to shipment with given algorithm.
 */
class ShipmentDriverAssignation(
    private val algorithm: IAssignationAlgorithm
) {

    /**
     * Execute the algorithm given with a set of drivers and shipments
     * @param drivers list with driver's names
     * @param shipments list with address
     *
     * @return list of drivers with shipment assigned
     */
    fun execute(
        drivers: List<Driver>,
        shipments: List<Shipment>
    ): List<Driver> {

        //Prepare all suitability scores for driver by shipments
        val input = prepareShipmentToDriverAssignation(drivers, shipments)

        //Execute algorithm and return value
        return algorithm.getBestMatching(input)
    }

    /**
     * Prepares the shipment to driver assignation as a 2D array for algorithm
     */
    private fun prepareShipmentToDriverAssignation(
        drivers: List<Driver>,
        shipments: List<Shipment>
    ): MutableList<MutableList<SuitabilityScore>> {
        val driversAssigned = mutableListOf<Driver>()
        val allSuitabilityScores = mutableListOf<MutableList<SuitabilityScore>>()

        //Prepare drivers name with vowels and consonants
        val preparedDrivers = drivers.map { driver -> prepareDriverName(driver) }

        //Determine suitability score fore each shipment
        shipments.forEachIndexed { i, shipment ->
            //Get common factors for shipments
            val shipmentsCF = shipment.address.length.commonFactors()

            // For all suitability score for current shipment address and a set of drivers
            val allSS = getSuitabilityScore(
                shipment = shipment,
                shipmentCommonFactors = shipmentsCF,
                drivers = preparedDrivers,
                ssFactor =
                if (shipment.address.length.isEven())  //If shipment's destination street name length
                    SuitabilityScoreFactor.EVEN
                else
                    SuitabilityScoreFactor.ODD
            )

            allSuitabilityScores[i] = (allSS)
        }

        return allSuitabilityScores
    }


    private fun getBestMatchingSuitabilityScore(suitabilityScores: List<SuitabilityScore>): List<Driver> {
        //Order suitability score list
        val ssList = suitabilityScores.toMutableList<SuitabilityScore>()
        ssList.sortBy { it.ss }
        //Get elements with best matching driver
        val drivers = ssList.distinctBy { it.driver }.map { it.driver.copy(shipment = it.shipment) }
        val shipments =
            ssList.distinctBy { it.shipment }.map { it.driver.copy(shipment = it.shipment) }
        //Return driver with shipment assigned
        return drivers
    }

    /**
     * Super secret algorithm, allows to get an array with SS according to certain factors
     */
    private fun getSuitabilityScore(
        shipment: Shipment,
        drivers: List<DriverPrepared>,
        ssFactor: SuitabilityScoreFactor,
        shipmentCommonFactors: List<Int>
    ): MutableList<SuitabilityScore> {
        val ssList = mutableListOf<SuitabilityScore>()
        ssList.addAll(drivers.map { driver ->
            //Calculate ss with vowels and consonants
            var ss = when (ssFactor) {
                SuitabilityScoreFactor.EVEN -> driver.vowels * SuitabilityScoreFactor.EVEN.value()
                SuitabilityScoreFactor.ODD -> driver.consonants * SuitabilityScoreFactor.ODD.value()
            }
            //Add common factors besides 1 to 50% over base (1.5 multiplier)
            ss *=
                if (driver.commonFactor.intersect(shipmentCommonFactors.toSet()).any())
                    1.5f else 1f
            SuitabilityScore(driver.driver, shipment, ss)
        })
        return ssList
    }

    /**
     * Prepare driver's name with vowels and consonants and common factors
     */
    private fun prepareDriverName(driver: Driver): DriverPrepared {
        val vowels = charArrayOf('A', 'E', 'I', 'O', 'U')

        //Prepare like -> NAME SURNAME -> NAMESURNAME
        val name = driver.name.trim().uppercase().filter { it.isLetter() }
        //Count all vowels
        val countVowels = name.toCharArray().count { letter -> vowels.contains(letter) }
        //Count all consonants
        val countConsonants = name.length - countVowels
        //Common factors of name's length
        val commonFactors = driver.name.length.commonFactors()
        return DriverPrepared(driver, name, countVowels, countConsonants, commonFactors)
    }

    internal data class DriverPrepared(
        val driver: Driver,
        val name: String,
        val vowels: Int,
        val consonants: Int,
        val commonFactor: List<Int>
    )

    data class SuitabilityScore(
        val driver: Driver,
        val shipment: Shipment,
        var ss: Float
    )

    internal enum class SuitabilityScoreFactor {
        EVEN {
            override fun value(): Float = 1.5f
        },
        ODD {
            override fun value(): Float = 1.0f
        };

        abstract fun value(): Float
    }
}


