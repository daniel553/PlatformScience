package com.tripletres.platformscience.domain.algorithm

import com.tripletres.platformscience.domain.ShipmentDriverAssignation
import com.tripletres.platformscience.domain.model.Driver
import com.tripletres.platformscience.domain.model.Shipment


fun ssMockList(major: Boolean = false): MutableList<MutableList<ShipmentDriverAssignation.SuitabilityScore>> {
    val matrix = mutableListOf<MutableList<ShipmentDriverAssignation.SuitabilityScore>>()
    val singleSS = arrayListOf(
        arrayListOf(9f, 2f, 7f, 8f),
        arrayListOf(6f, 4f, 3f, 7f),
        arrayListOf(5f, 8f, 1f, 8f),
        arrayListOf(7f, 6f, 9f, 4f)
    )

    val mayorSS = arrayListOf(
        arrayListOf(11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f),
        arrayListOf(9.0f, 10.5f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f),
        arrayListOf(11.25f, 13.5f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f),
        arrayListOf(13.5f, 6.0f, 13.5f, 6.0f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f),
        arrayListOf(11.25f, 12.0f, 11.25f, 12.0f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f, 11.25f),
        arrayListOf(9.0f, 10.5f, 9.0f, 10.5f, 9.0f, 10.5f, 11.25f, 11.25f, 11.25f, 11.25f),
        arrayListOf(11.25f, 10.5f, 11.25f, 10.5f, 11.25f, 10.5f, 11.25f, 11.25f, 11.25f, 11.25f),
        arrayListOf(9.0f, 10.5f, 9.0f, 10.5f, 9.0f, 10.5f, 9.0f, 10.5f, 11.25f, 11.25f),
        arrayListOf(9.0f, 15.0f, 9.0f, 15.0f, 9.0f, 15.0f, 9.0f, 15.0f, 9.0f, 11.25f),
        arrayListOf(11.25f, 7.5f, 11.25f, 7.5f, 11.25f, 7.5f, 11.25f, 7.5f, 11.25f, 7.5f)
    )
    val n = if(major) mayorSS.size else singleSS.size

    for (i in (0..n.minus(1))) {
        val mj = mutableListOf<ShipmentDriverAssignation.SuitabilityScore>()
        for (j in (0..n.minus(1))) {
            val mock = ssMock(i, j, if(major)mayorSS[i][j] else singleSS[i][j])
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
