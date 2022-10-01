package com.tripletres.platformscience.ui.view.driver

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripletres.platformscience.R

/**
 * Summary rows like a dashboard
 */
@Composable
fun DriverSummaryRow(count: Int, totalSS: Float) {
    Row(
        modifier = Modifier.padding(top = 16.dp)
    ) {

        Box(modifier = Modifier.weight(1f)) {
            DriverHighlightCard(
                title = stringResource(id = R.string.driver_list_summary_title_count),
                details = count.toString(),
                icon = Icons.Outlined.Person)
        }

        Box(modifier = Modifier.weight(1f)) {
            DriverHighlightCard(
                title = stringResource(id = R.string.driver_list_summary_title_ss),
                details = totalSS.toString(),
                icon = Icons.Outlined.Place)
        }

    }
}

@Composable
fun DriverHighlightCard(title: String, details: String, icon: ImageVector) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 2.dp,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 32.dp, start = 16.dp, end = 32.dp)
            ) {
                Text(text = title,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = details,
                    style = MaterialTheme.typography.h5
                )
            }
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier
                    .padding(16.dp)
                    .scale(1.5f)
                    .alpha(0.8f)
            )
        }
    }
}

@Preview
@Composable
fun DriverHighlightCardPreview() {
    DriverHighlightCard(title = "TITLE",
        details = "DETAILS",
        icon = Icons.Outlined.Person)
}