package com.tripletres.platformscience.ui.view.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tripletres.platformscience.R

/**
 * Simple settings view
 */
@Composable
fun SimpleSettingsView(modifier: Modifier? = Modifier) {
    val viewModel: SimpleSettingsViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()
    Column(modifier = modifier ?: Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = uiState.value.dbOrApi,
                onCheckedChange = { viewModel.updateDbOrApi(!uiState.value.dbOrApi) })

            Text(text =
            "${stringResource(id = R.string.setting_db_or_api)}: ${uiState.value.dbOrApiLabel}")
        }
        Divider()
        Text(
            text = stringResource(id = R.string.algorithm).uppercase(),
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(top = 8.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            uiState.value.algorithms.forEach { algorithm ->
                RadioButton(selected = algorithm == uiState.value.algorithmSelected, onClick = {
                    viewModel.updateAlgorithmSelected(algorithm)
                })
                Text(
                    text = stringResource(
                        id = when (algorithm) {

                            else -> R.string.algorithm_greedy
                        }))
            }
        }
    }
}

