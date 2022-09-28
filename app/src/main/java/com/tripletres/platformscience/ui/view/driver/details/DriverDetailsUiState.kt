package com.tripletres.platformscience.ui.view.driver.details

import com.tripletres.platformscience.ui.model.DriverItem

data class DriverDetailsUiState(
    val isLoading: Boolean = false,
    val driverId: Long = 0L,
    val driver: DriverItem? = null
)
