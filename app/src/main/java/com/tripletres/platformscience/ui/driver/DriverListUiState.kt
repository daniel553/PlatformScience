package com.tripletres.platformscience.ui.driver

import com.tripletres.platformscience.ui.model.DriverItem

data class DriverListUiState(
    val isLoading: Boolean = false,
    val drivers: List<DriverItem> = emptyList()
)
