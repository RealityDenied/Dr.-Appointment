package com.uilover.project255.feature.specialization

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uilover.project255.R
import com.uilover.project255.core.model.DoctorModel
import com.uilover.project255.feature.topdoctors.components.DoctorsRowCard

@Composable
fun DoctorsBySpecializationScreen(
    specializationName: String,
    doctors: List<DoctorModel>,
    onBack: () -> Unit,
    onDoctorClick: (DoctorModel) -> Unit
) {
    val listState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .height(56.dp)
                ) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            painterResource(R.drawable.back),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                    Text(
                        text = specializationName,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            items(
                items = doctors,
                key = { it.Name + "_" + (it.Mobile ?: "") + "_" + it.Id }
            ) { doc ->
                DoctorsRowCard(
                    item = doc,
                    onMakeClick = { onDoctorClick(doc) }
                )
            }
        }
        if (doctors.isEmpty()) {
            Text(
                text = "No doctors in this specialization",
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
