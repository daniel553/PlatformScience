package com.tripletres.platformscience.ui.view.driver.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripletres.platformscience.domain.GetAssignedDriversToShipmentsUseCase
import com.tripletres.platformscience.domain.GetDriverDetailsUseCase
import com.tripletres.platformscience.ui.model.DriverItem
import com.tripletres.platformscience.ui.model.asDriverItem
import com.tripletres.platformscience.util.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverDetailsViewModel @Inject constructor(
    private val getDriverDetailsUseCase: GetDriverDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(DriverDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun setDriverId(driverId: Long?) {
        driverId?.let { id ->
            _uiState.update { it.copy(driverId = id.toLong()) }
        }
    }

    fun getUserFromDB() {
        LogUtils.d("-----> getUserFromDB: ${uiState.value.driverId}")
        viewModelScope.launch(Dispatchers.IO) {
            updateDriver(getDriverDetailsUseCase(uiState.value.driverId).asDriverItem())
        }
    }

    private fun updateDriver(driver: DriverItem) {
        _uiState.update { state -> state.copy(driver = driver) }
    }

}