package com.tripletres.platformscience.ui.driver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripletres.platformscience.data.db.driver.DriverEntity
import com.tripletres.platformscience.data.repo.DriverRepository
import com.tripletres.platformscience.ui.model.DriverItem
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
    //TODO: inject use case
    private val driverRepository: DriverRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(DriverListUiState())
    val uiState = _uiState.asStateFlow()

    fun loadDriverList() {
        viewModelScope.launch(Dispatchers.IO) {
            updateLoading(true)
            val drivers = driverRepository.getDriversFromDB()
            if(drivers.isNotEmpty()) {
                updateDriverList(drivers.asDriverItemList())
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

private fun List<DriverEntity>.asDriverItemList(): List<DriverItem> {
    return this.map { it.asDriverItem() }
}

private fun DriverEntity.asDriverItem(): DriverItem {
    return DriverItem(this.id, this.name)
}
