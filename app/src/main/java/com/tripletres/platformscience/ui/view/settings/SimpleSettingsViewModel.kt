package com.tripletres.platformscience.ui.view.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripletres.platformscience.ui.view.driver.DriverListUiState
import com.tripletres.platformscience.util.LogUtils
import com.tripletres.platformscience.util.SimpleSettingsUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SimpleSettingsViewModel @Inject constructor(
    private val settings: SimpleSettingsUtil,
) : ViewModel() {
    private val _uiState = MutableStateFlow(fromSettings())
    val uiState = _uiState.asStateFlow()

    fun updateDbOrApi(state: Boolean) {
        val dbOrApi = if (state) SimpleSettingsUtil.DB_OR_API_DEF else SimpleSettingsUtil.DB_OR_API_API
            settings.setPreference(SimpleSettingsUtil.DB_OR_API, dbOrApi)

    }

    fun updateAlgorithmSelected(algorithm: String) {
       settings.setPreference(SimpleSettingsUtil.ALGORITHM, algorithm)
    }

    /**
     * Loads from settings ui state
     */
    private fun fromSettings(): SimpleSettingsUiState {
        val dbOrApiLabel = settings.getPreference(SimpleSettingsUtil.DB_OR_API)
        return SimpleSettingsUiState(
            dbOrApi = dbOrApiLabel == SimpleSettingsUtil.DB_OR_API_DEF,
            dbOrApiLabel = dbOrApiLabel,
            algorithms = SimpleSettingsUtil.allAlgorithms(),
            algorithmSelected = settings.getPreference(SimpleSettingsUtil.ALGORITHM)
        )
    }

    /**
     * Call update state when saved to shared preferences
     */
    private fun updateState(newState: SimpleSettingsUtil) =_uiState.update { fromSettings() }

}