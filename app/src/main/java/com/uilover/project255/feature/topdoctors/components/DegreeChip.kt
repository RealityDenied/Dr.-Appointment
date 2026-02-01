package com.uilover.project255.feature.topdoctors.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uilover.project255.R

@Composable
fun DegreeChip(text: String) {
    val lightPurple = colorResource(R.color.lightPurple)
    val purple = colorResource(R.color.purple)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(25.dp)
            .background(lightPurple, RoundedCornerShape(100.dp))
            .padding(horizontal = 8.dp)
    ) {
        Image(
            painterResource(R.drawable.tick),
            contentDescription = null
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = text,
            color = purple,
            fontSize = MaterialTheme.typography.labelMedium.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun DegreeChipPreview() {
    DegreeChip(text = "Doctor of Medicine")
}
