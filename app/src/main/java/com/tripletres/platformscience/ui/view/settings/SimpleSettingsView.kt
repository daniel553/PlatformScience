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
import com.tripletres.platformscience.util.SimpleSettingsUtil.Companion.ALGORITHM_BRANCH_BOUND
import com.tripletres.platformscience.util.SimpleSettingsUtil.Companion.ALGORITHM_GREEDY

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
        uiState.value.algorithms.forEach { algorithm ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = algorithm == uiState.value.algorithmSelected, onClick = {
                    viewModel.updateAlgorithmSelected(algorithm)
                })
                Text(
                    text = stringResource(
                        id = when (algorithm) {
                            ALGORITHM_GREEDY -> R.string.algorithm_greedy
                            ALGORITHM_BRANCH_BOUND -> R.string.algorithm_branch_and_bound
                            else -> R.string.algorithm_greedy
                        }))
            }
        }
    }
}

