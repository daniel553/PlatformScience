package com.tripletres.platformscience.ui.view.driver

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tripletres.platformscience.R
import com.tripletres.platformscience.ui.view.settings.SimpleSettingsView

/**
 * Simple "Settings" dialog to modify how to reload the data
 */
@Composable
fun DriverListReloadDialog(onDismiss: (confirm: Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = {
            onDismiss(false)
        },
        title = {
            Text(
                text = stringResource(id = R.string.driver_reload_title).uppercase()
            )
        },
        text = {
            SimpleSettingsView()
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = { onDismiss(false) }) {
                    Text(text = stringResource(id = R.string.cancel))
                }
                Button(
                    modifier = Modifier.padding(start = 8.dp),
                    onClick = { onDismiss(true) }
                ) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        }
    )
}