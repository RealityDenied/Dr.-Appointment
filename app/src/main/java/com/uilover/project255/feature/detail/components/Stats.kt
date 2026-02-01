package com.uilover.project255.feature.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uilover.project255.R

@Composable
fun RowScope.StateColumn(title: String, value: String) {
    val black = Color.Black
    val purple = colorResource(R.color.purple)

    Column(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, color = black, textAlign = TextAlign.Center)
        Spacer(Modifier.height(8.dp))
        Text(
            text = value,
            color = purple,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun StateColumnPreview() {
    Row {
        StateColumn(title = "HP", value = "100")
    }
}

@Composable
fun RowScope.RatingStat(title: String, rating: Double) {
    val black = Color.Black
    val purple = colorResource(R.color.purple)

    Column(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, color = black, textAlign = TextAlign.Center)
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(R.drawable.star), contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text(text = String.format("%.1f", rating), color = purple, fontWeight = FontWeight.Bold)
        }
    }
}
