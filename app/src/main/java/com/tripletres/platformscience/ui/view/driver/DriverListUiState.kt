package com.tripletres.platformscience.ui.view.driver

import com.tripletres.platformscience.ui.model.DriverItem

data class DriverListUiState(
    val isLoading: Boolean = false,
    val drivers: List<DriverItem> = emptyList(),
    val isReloadDialog: Boolean = false,
    val totalSS: Float = 0f
)
