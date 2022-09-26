package com.tripletres.platformscience.ui.driver

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tripletres.platformscience.R
import com.tripletres.platformscience.ui.model.DriverItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DriverListViewScreen(navController: NavController, viewModel: DriverListViewModel) {
    val driverUiState by viewModel.uiState.collectAsState()
    viewModel.loadDriverList()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Drivers")
            })
        },
        content = {
            DriverListView(driverUiState.drivers)
        },
        modifier = Modifier.fillMaxSize()
    )

}

@Composable
fun DriverListView(drivers: List<DriverItem>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(drivers) { driver ->
            DriverItemView(driver) {
                //TODO: pressed.
            }
        }
    }
}

@Composable
fun DriverItemView(driver: DriverItem, onPressed: (id: Long) -> Unit) {
    Card(
        //colors = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "",
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(CircleShape)
            )
            Badge() {
                Text(text = driver.id.toString())
            }
            Text(
                text = driver.name,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
            IconButton(
                onClick = { onPressed(driver.id) },
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
            }
        }
    }
}