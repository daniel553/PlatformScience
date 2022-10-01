package com.tripletres.platformscience.ui.view.driver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripletres.platformscience.domain.AssignDriversToShipmentsUseCase
import com.tripletres.platformscience.domain.GetAssignedDriversToShipmentsUseCase
import com.tripletres.platformscience.domain.LoadDriversShipmentsUseCase
import com.tripletres.platformscience.ui.model.DriverItem
import com.tripletres.platformscience.ui.model.asDriverItemList
import com.tripletres.platformscience.util.LogUtils
import com.tripletres.platformscience.util.ProfileUtil
import com.tripletres.platformscience.util.SimpleSettingsUtil
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
    private val getAssignedDriversToShipmentsUseCase: GetAssignedDriversToShipmentsUseCase,
    private val loadDriversShipmentsUseCase: LoadDriversShipmentsUseCase,
    private val assignDriversToShipmentsUseCase: AssignDriversToShipmentsUseCase,
    private val settings: SimpleSettingsUtil,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DriverListUiState())
    val uiState = _uiState.asStateFlow()

    fun loadDriverList(accept: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            updateLoading(true)

            //Determine if "Accepted button was performed"
            //So we need to "reload" with new user settings
            if (accept) {
                loadDriversShipmentsUseCase(getCachedSetting())
                assignDriversToShipmentsUseCase(getAlgorithmSetting())
            }

            val drivers = getAssignedDriversToShipmentsUseCase().asDriverItemList()

            if (drivers.isNotEmpty()) {
                updateDriverList(beautifyProfile(drivers))
                updateLoading(false)
            }
        }
    }

    fun showReloadDialog(show: Boolean) {
        _uiState.update { state -> state.copy(isReloadDialog = show) }
    }

    private fun beautifyProfile(drivers: List<DriverItem>): List<DriverItem> {
        return drivers.mapIndexed { i, it -> it.copy(profilePic = ProfileUtil.getByIndex(i)) }
    }

    private fun updateDriverList(drivers: List<DriverItem>) {
        _uiState.update {
            it.copy(
                drivers = drivers,
                totalSS = getTotalSS(drivers)
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

    private fun getTotalSS(drivers: List<DriverItem>): Float {
        return drivers.map { it.ss }.reduce { acc, fl -> acc?.plus(fl!!) } ?: 0f
    }

    private fun getCachedSetting() =
        settings.getPreference(SimpleSettingsUtil.DB_OR_API) == SimpleSettingsUtil.DB_OR_API_DEF

    private fun getAlgorithmSetting() = settings.getPreference(SimpleSettingsUtil.ALGORITHM)
}

