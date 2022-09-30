package com.tripletres.platformscience.domain.algorithm

import com.tripletres.platformscience.domain.ShipmentDriverAssignation
import com.tripletres.platformscience.domain.model.Driver
import com.tripletres.platformscience.domain.model.Shipment


fun ssMockList(): MutableList<MutableList<ShipmentDriverAssignation.SuitabilityScore>> {
    val matrix = mutableListOf<MutableList<ShipmentDriverAssignation.SuitabilityScore>>()
    val singleSS = arrayListOf(
        arrayListOf(9f, 2f, 7f, 8f),
        arrayListOf(6f, 4f, 3f, 7f),
        arrayListOf(5f, 8f, 1f, 8f),
        arrayListOf(7f, 6f, 9f, 4f)
    )

    for (i in (0..3)) {
        val mj = mutableListOf<ShipmentDriverAssignation.SuitabilityScore>()
        for (j in (0..3)) {
            val mock = ssMock(i, j, singleSS[i][j])
            mj.add(mock)
        }
        matrix.add(mj)
    }

    return matrix
}

fun ssMock(i: Int, j: Int, ss: Float): ShipmentDriverAssignation.SuitabilityScore {
    return ShipmentDriverAssignation.SuitabilityScore(
        driver = Driver(
            id = i.toLong(),
            name = "Driver $i",
            shipment = null,
            ss = ss
        ),
        shipment = Shipment(
            id = j.toLong(),
            address = "Address $j"
        ),
        ss = ss
    )

}
