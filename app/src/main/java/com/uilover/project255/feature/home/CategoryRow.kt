package com.uilover.project255.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uilover.project255.R
import com.uilover.project255.core.data.SpecializationItem

@Composable
private fun SpecializationItemView(
    item: SpecializationItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 8.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(colorResource(R.color.lightPurple)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.name,
                modifier = Modifier.size(30.dp),
                tint = colorResource(R.color.darkPurple)
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = item.name,
            color = colorResource(R.color.darkPurple)
        )
    }
}

@Composable
fun CategoryRow(
    items: List<SpecializationItem>,
    onSpecializationClick: (String) -> Unit = {}
) {
    Box(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp)
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(items) { item ->
                SpecializationItemView(
                    item = item,
                    onClick = { onSpecializationClick(item.name) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun CategoryRowPreview() {
    CategoryRow(items = listOf(
        SpecializationItem(1, "Cardiology", Icons.Filled.MedicalServices),
        SpecializationItem(2, "Dentistry", Icons.Filled.MedicalServices)
    ))
}
