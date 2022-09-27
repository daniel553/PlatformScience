package com.tripletres.platformscience.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripletres.platformscience.domain.AssignDriversToShipmentsUseCase
import com.tripletres.platformscience.domain.LoadDriversShipmentsUseCase
import com.tripletres.platformscience.ui.model.DriverItem
import com.tripletres.platformscience.util.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main activity view model class. Allows to update the initial view state
 * between loading components with splash and data loads
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val loadDriversShipmentsUseCase: LoadDriversShipmentsUseCase,
    private val assignDriversToShipmentsUseCase: AssignDriversToShipmentsUseCase,
): ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState: StateFlow<MainUiState> = _mainUiState.asStateFlow()

    init {
        fetchShipmentsWithDrivers()
    }

    private fun fetchShipmentsWithDrivers() {
        showLoading(true)
        viewModelScope.launch(Dispatchers.IO) {
            loadDriversShipmentsUseCase()
            assignDriversToShipmentsUseCase()
            val drivers: List<DriverItem> = emptyList()
            LogUtils.d("Drivers: ${drivers.toString()}")
            updateDrivers(drivers)
            showLoading(false)
        }
    }

    private fun showLoading(loading: Boolean) {
        _mainUiState.update {
            it.copy(isLoading = loading )
        }
    }

    private fun updateDrivers(drivers: List<DriverItem>) {
        _mainUiState.update {
            it.copy(drivers = drivers)
        }
    }

}