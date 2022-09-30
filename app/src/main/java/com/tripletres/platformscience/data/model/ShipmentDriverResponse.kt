package com.tripletres.platformscience.data.model

/**
 * Response that brings shipments and drivers:
 * {
 *  shipments: ["ABC", "DEF", ...],
 *  drivers: ["Tony", "Sandy", ...]
 * }
 */
data class ShipmentDriverResponse (val shipments: List<String>, val drivers: List<String>)