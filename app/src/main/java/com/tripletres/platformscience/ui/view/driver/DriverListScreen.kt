package com.tripletres.platformscience.ui.view.driver

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tripletres.platformscience.R
import com.tripletres.platformscience.ui.model.DriverItem
import com.tripletres.platformscience.ui.model.ShipmentItem
import com.tripletres.platformscience.ui.navigation.Router

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
            DriverListView(driverUiState.drivers) {
                navController.navigate(
                    route = Router.DriverDetailsScreen.buildRoute(it.id.toString())
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )

}

@Composable
fun DriverListView(drivers: List<DriverItem>, onDriverSelected: (driver: DriverItem) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        items(drivers) { driver ->
            DriverItemView(driver) {
                onDriverSelected(driver)
            }
        }
    }
}

@Composable
fun DriverItemView(driver: DriverItem, onPressed: (id: Long) -> Unit) {
    Box() {
        Card(
            backgroundColor = MaterialTheme.colors.background,
            elevation = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp)
                .padding(start = 40.dp, end = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 48.dp)
            ) {

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

        Image(
            painter = painterResource(id = R.drawable.ic_man),
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
            ShipmentItem(10, "Address"),
            1.5f
        )
    ) {}
}