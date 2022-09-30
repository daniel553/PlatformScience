package com.tripletres.platformscience.ui.view.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripletres.platformscience.domain.AssignDriversToShipmentsUseCase
import com.tripletres.platformscience.domain.LoadDriversShipmentsUseCase
import com.tripletres.platformscience.ui.model.DriverItem
import com.tripletres.platformscience.util.LogUtils
import com.tripletres.platformscience.util.SimpleSettingsUtil
import com.tripletres.platformscience.util.SimpleSettingsUtil.Companion.ALGORITHM
import com.tripletres.platformscience.util.SimpleSettingsUtil.Companion.DB_OR_API
import com.tripletres.platformscience.util.SimpleSettingsUtil.Companion.DB_OR_API_DEF
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
    private val settings: SimpleSettingsUtil,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchShipmentsWithDrivers()
    }

    private fun fetchShipmentsWithDrivers() {
        viewModelScope.launch(Dispatchers.IO) {
            loadDriversShipmentsUseCase(getCachedSetting())
            updateStatus(SplashUiStatus.ASSIGNING)
            assignDriversToShipmentsUseCase(getAlgorithmSetting())
            updateStatus(SplashUiStatus.DONE)
        }
    }

    private fun updateStatus(newStatus: SplashUiStatus) {
        _uiState.update { state -> state.copy(status = newStatus) }
    }

    private fun getCachedSetting() = settings.getPreference(DB_OR_API) == DB_OR_API_DEF
    private fun getAlgorithmSetting() = settings.getPreference(ALGORITHM)

}