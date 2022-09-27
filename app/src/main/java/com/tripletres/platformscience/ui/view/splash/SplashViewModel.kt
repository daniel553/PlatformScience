package com.tripletres.platformscience.ui.view.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripletres.platformscience.domain.AssignDriversToShipmentsUseCase
import com.tripletres.platformscience.domain.LoadDriversShipmentsUseCase
import com.tripletres.platformscience.ui.model.DriverItem
import com.tripletres.platformscience.util.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Load all needed to show main view after
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loadDriversShipmentsUseCase: LoadDriversShipmentsUseCase,
    private val assignDriversToShipmentsUseCase: AssignDriversToShipmentsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchShipmentsWithDrivers()
    }

    private fun fetchShipmentsWithDrivers() {
        viewModelScope.launch(Dispatchers.IO) {
            loadDriversShipmentsUseCase()
            updateStatus(SplashUiStatus.ASSIGNING)
            assignDriversToShipmentsUseCase()
            updateStatus(SplashUiStatus.DONE)
            val drivers: List<DriverItem> = emptyList()
            LogUtils.d("Drivers: ${drivers.toString()}")
        }
    }

    private fun updateStatus(newStatus: SplashUiStatus) {
        _uiState.update { state -> state.copy(status = newStatus) }
    }

}