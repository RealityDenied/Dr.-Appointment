package com.uilover.project255.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.uilover.project255.R
import com.uilover.project255.core.ViewModel.AccountViewModel
import com.uilover.project255.core.model.AppointmentModel
import com.uilover.project255.core.model.DoctorModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DoctorAccountContent(
    viewModel: AccountViewModel,
    currentEmail: String,
    doctorKey: String?,
    doctor: DoctorModel?,
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
        if (doctor == null) {
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
            if (doctor.Picture.isNotBlank()) {
                AsyncImage(
                    model = doctor.Picture,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = colorResource(R.color.darkPurple)
                )
            }
        }
        Spacer(Modifier.height(12.dp))
        Text(
            text = doctor.Name,
            fontSize = 22.sp,
            color = colorResource(R.color.darkPurple),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = currentEmail,
            fontSize = 14.sp,
            color = colorResource(R.color.gray),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Text(text = "Specialization: ${doctor.Special}", fontSize = 14.sp, color = colorResource(R.color.darkPurple), modifier = Modifier.fillMaxWidth())
        Text(text = "Patients: ${doctor.Patiens}", fontSize = 14.sp, color = colorResource(R.color.darkPurple), modifier = Modifier.fillMaxWidth())
        Text(text = "Phone: ${doctor.Mobile}", fontSize = 14.sp, color = colorResource(R.color.darkPurple), modifier = Modifier.fillMaxWidth())
        Text(text = "Experience: ${doctor.Expriense} years", fontSize = 14.sp, color = colorResource(R.color.darkPurple), modifier = Modifier.fillMaxWidth())
        Text(text = "Location: ${doctor.Location}", fontSize = 14.sp, color = colorResource(R.color.darkPurple), modifier = Modifier.fillMaxWidth())
        Text(text = "Biography: ${doctor.Biography}", fontSize = 14.sp, color = colorResource(R.color.darkPurple), modifier = Modifier.fillMaxWidth())
        if (doctor.Site.isNotBlank()) Text(text = "Website: ${doctor.Site}", fontSize = 14.sp, color = colorResource(R.color.darkPurple), modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.upcoming_appointments),
            fontSize = 18.sp,
            color = colorResource(R.color.darkPurple),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        if (appointments.isEmpty()) {
            Text(
                text = stringResource(R.string.no_appointments),
                fontSize = 14.sp,
                color = colorResource(R.color.gray),
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            val dateFormat = SimpleDateFormat("MMM d, yyyy 'at' h:mm a", Locale.getDefault())
            appointments.forEach { apt ->
                val dateStr = dateFormat.format(Date(apt.dateTimeMillis))
                Text(
                    text = "${apt.patientName} - $dateStr",
                    fontSize = 14.sp,
                    color = colorResource(R.color.darkPurple),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
            }
        }
        Spacer(Modifier.height(24.dp))
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
