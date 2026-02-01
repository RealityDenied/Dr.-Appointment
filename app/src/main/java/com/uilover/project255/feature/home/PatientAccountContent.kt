package com.uilover.project255.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uilover.project255.R
import com.uilover.project255.core.model.AppointmentModel
import com.uilover.project255.core.model.PatientModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PatientAccountContent(
    patient: PatientModel?,
    appointments: List<AppointmentModel>,
    onLogout: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp)
    ) {
        if (patient == null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Column
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
                .background(colorResource(R.color.lightPurple), RoundedCornerShape(50.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = colorResource(R.color.darkPurple)
            )
        }
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.patient_profile),
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(R.color.darkPurple),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Text(text = "Name: ${patient.name}", fontSize = 16.sp, color = colorResource(R.color.darkPurple), modifier = Modifier.fillMaxWidth())
        Text(text = "Phone: ${patient.phone}", fontSize = 16.sp, color = colorResource(R.color.darkPurple), modifier = Modifier.fillMaxWidth())
        Text(text = "Email: ${patient.email}", fontSize = 16.sp, color = colorResource(R.color.darkPurple), modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.upcoming_appointments),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(R.color.darkPurple),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        if (appointments.isEmpty()) {
            Text(
                text = stringResource(R.string.no_appointments),
                fontSize = 14.sp,
                color = colorResource(R.color.gray),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            val dateFormat = SimpleDateFormat("MMM d, yyyy 'at' h:mm a", Locale.getDefault())
            appointments.forEach { apt ->
                val dateStr = dateFormat.format(Date(apt.dateTimeMillis))
                Text(
                    text = "${apt.doctorName} - $dateStr",
                    fontSize = 14.sp,
                    color = colorResource(R.color.darkPurple),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
            }
        }
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.darkPurple), contentColor = colorResource(R.color.white))
        ) {
            Text(stringResource(R.string.logout), fontSize = 18.sp)
        }
    }
}
