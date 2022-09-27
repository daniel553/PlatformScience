package com.tripletres.platformscience.domain.algorithm

import com.tripletres.platformscience.domain.ShipmentDriverAssignation.SuitabilityScore
import com.tripletres.platformscience.domain.model.Driver

/**
 * Assignation problem algorithm interface
 */
interface IAssignationAlgorithm {

    /**
     * At least get the main best matching that from SS that host drivers and shipments
     * a list of drivers are returned
     */
    fun getBestMatching(input: MutableList<MutableList<SuitabilityScore>>): List<Driver>
}