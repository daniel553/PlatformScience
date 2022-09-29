package com.tripletres.platformscience.ui.view.driver

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tripletres.platformscience.R
import com.tripletres.platformscience.ui.model.DriverItem
import com.tripletres.platformscience.ui.model.ShipmentItem
import com.tripletres.platformscience.ui.navigation.Router

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
            DriverListView(driverUiState.drivers) {
                navController.navigate(
                    route = Router.DriverDetailsScreen.buildRoute(it.id.toString())
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.showReloadDialog(true) }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
            }
        },
        modifier = Modifier.fillMaxSize()
    )

    if (driverUiState.isReloadDialog) {
        DriverListReloadDialog { confirm ->
            if (confirm) {
                viewModel.loadDriverList()
            }
            viewModel.showReloadDialog(false)
        }
    }

}

@Composable
fun DriverListView(drivers: List<DriverItem>, onDriverSelected: (driver: DriverItem) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        item() {
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(drivers) { driver ->
            DriverItemView(driver) {
                onDriverSelected(driver)
            }
        }
        item() {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun DriverItemView(driver: DriverItem, onPressed: (id: Long) -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onPressed(driver.id) }
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = MaterialTheme.colors.background,
            elevation = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp)
                .padding(start = 40.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 48.dp)
            ) {
                Column() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "ID:",
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Badge(
                            backgroundColor = MaterialTheme.colors.secondaryVariant,
                            contentColor = Color.White
                        ) {
                            Text(text = driver.id.toString())
                        }
                        Text(
                            text = driver.name.uppercase(),
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    driver.shipmentItem?.let {
                        Text(
                            text = stringResource(id = R.string.driver_assigned_route)
                                .plus(it.address.take(12).plus("...")),
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.secondaryVariant
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
            }
        }

        Image(
            painter = painterResource(id = driver.profilePic ?: R.drawable.ic_man),
            contentDescription = "",
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DriverItemViewPreview() {
    DriverItemView(
        DriverItem(
            10,
            "Pedro Daniel Garcia",
            ShipmentItem(10, "Very long address 123"),
            1.5f
        )
    ) {}
}