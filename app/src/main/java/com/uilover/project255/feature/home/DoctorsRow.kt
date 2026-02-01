package com.uilover.project255.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.uilover.project255.R
import com.uilover.project255.core.model.DoctorModel


@Composable
private fun DoctorCard(item: DoctorModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .size(width = 220.dp, height = 300.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(196.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(R.color.lightPurple)),
                contentAlignment = Alignment.Center
            ) {
                if (item.Picture.isNotBlank()) {
                    AsyncImage(
                        model = item.Picture,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = colorResource(R.color.darkPurple)
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = item.Name ?: "name",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = item.Special ?: "special",
                color = colorResource(R.color.gray),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.star),
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = (item.Rating ?: 0.0).toString(),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Spacer(Modifier.weight(1f))

                Image(
                    painter = painterResource(R.drawable.experience),
                    contentDescription = null
                )

                Spacer(Modifier.width(6.dp))

                Text(
                    text = "${item.Expriense ?: 0} Year",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun DoctorRow(
    items: List<DoctorModel>,
    onClick: (DoctorModel) -> Unit
) {
    Box(
        Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .heightIn(min = 320.dp)
    ) {
        if (items.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(items) { item ->
                    DoctorCard(item = item, onClick = { onClick(item) })
                }
            }
        }
    }
}


@Preview
@Composable
private fun DoctorCardPreview() {
    val item = DoctorModel(Name = "Dr. John Doe", Special = "Cardiology", Rating = 4.5)
    DoctorCard(item = item, onClick = {})
}