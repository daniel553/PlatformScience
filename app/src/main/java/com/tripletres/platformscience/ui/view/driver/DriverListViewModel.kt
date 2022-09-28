package com.tripletres.platformscience.ui.view.driver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripletres.platformscience.data.db.driver.DriverEntity
import com.tripletres.platformscience.data.repo.DriverRepository
import com.tripletres.platformscience.domain.GetAssignedDriversToShipmentsUseCase
import com.tripletres.platformscience.ui.model.DriverItem
import com.tripletres.platformscience.ui.model.asDriverItemList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Driver list view model, actions for UI state for drivers and its lists
 */
@HiltViewModel
class DriverListViewModel @Inject constructor(
    private val getAssignedDriversToShipmentsUseCase: GetAssignedDriversToShipmentsUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(DriverListUiState())
    val uiState = _uiState.asStateFlow()

    fun loadDriverList() {
        viewModelScope.launch(Dispatchers.IO) {
            updateLoading(true)
            val drivers = getAssignedDriversToShipmentsUseCase().asDriverItemList()
            if(drivers.isNotEmpty()) {
                updateDriverList(drivers)
                updateLoading(false)
            }
        }
    }

    private fun updateDriverList(drivers: List<DriverItem>) {
        _uiState.update{
            it.copy(
                drivers = drivers
            )
        }
    }

    private fun updateLoading(loading: Boolean) {
        _uiState.update {
            it.copy(
                isLoading = loading
            )
        }
    }
}

