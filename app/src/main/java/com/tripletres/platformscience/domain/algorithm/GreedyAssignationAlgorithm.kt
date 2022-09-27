package com.tripletres.platformscience.domain.algorithm

import com.tripletres.platformscience.domain.ShipmentDriverAssignation.SuitabilityScore
import com.tripletres.platformscience.domain.model.Driver

/**
 * Simple greedy assignation problem algorithm
 */
class GreedyAssignationAlgorithm() : IAssignationAlgorithm {

    override fun getBestMatching(input: MutableList<MutableList<SuitabilityScore>>): List<Driver> {
        //Prepare list
        val list = mutableListOf<SuitabilityScore>()
        input.forEach { i -> i.forEach { j -> list.add(j) } }
        // Just use kotlin functions to sort them
        list.sortBy { it.ss }
        //Get bets matching by making a distinct driver
        return list.distinctBy { it.driver }.map { it.driver.copy(shipment = it.shipment) }
    }
}