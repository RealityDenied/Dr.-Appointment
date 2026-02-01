package com.uilover.project255.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.uilover.project255.R
import com.uilover.project255.core.ViewModel.AccountRole
import com.uilover.project255.core.ViewModel.AccountViewModel
import com.uilover.project255.core.ViewModel.AppointmentViewModel

@Composable
fun AccountContent(
    viewModel: AccountViewModel,
    appointmentViewModel: AppointmentViewModel,
    onLogout: () -> Unit,
    onBackToHome: () -> Unit
) {
    val role by viewModel.role.observeAsState(AccountRole.None)
    val doctorProfile by viewModel.doctorProfile.observeAsState(null)
    val patientProfile by viewModel.patientProfile.observeAsState(null)
    val doctorKey by viewModel.doctorKey.observeAsState(null)
    val loading by viewModel.loading.observeAsState(false)
    val patientAppointments by appointmentViewModel.patientAppointments.observeAsState(initial = emptyList())
    val doctorAppointments by appointmentViewModel.doctorAppointments.observeAsState(initial = emptyList())
    val currentEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""
    val patientUid = FirebaseAuth.getInstance().currentUser?.uid

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }
    LaunchedEffect(role, doctorKey, patientUid, doctorProfile, patientProfile) {
        when (role) {
            AccountRole.Patient -> if (patientProfile != null) patientUid?.let { appointmentViewModel.loadPatientAppointments(it) }
            AccountRole.Doctor -> if (doctorProfile != null) doctorKey?.let { appointmentViewModel.loadDoctorAppointments(it) }
            else -> { }
        }
    }

    when {
        loading && role == AccountRole.None -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        role == AccountRole.Doctor && doctorProfile != null -> {
            Column(Modifier.fillMaxSize()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onBackToHome) {
                        Text(stringResource(R.string.back), color = colorResource(R.color.darkPurple), fontSize = 16.sp)
                    }
                }
                DoctorAccountContent(
                    viewModel = viewModel,
                    currentEmail = currentEmail,
                    doctorKey = doctorKey,
                    doctor = doctorProfile,
                    appointments = doctorAppointments.orEmpty(),
                    onLogout = onLogout
                )
            }
        }
        role == AccountRole.Patient && patientProfile != null -> {
            Column(Modifier.fillMaxSize()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onBackToHome) {
                        Text(stringResource(R.string.back), color = colorResource(R.color.darkPurple), fontSize = 16.sp)
                    }
                }
                PatientAccountContent(
                    patient = patientProfile,
                    appointments = patientAppointments.orEmpty(),
                    onLogout = onLogout
                )
            }
        }
        role == AccountRole.Doctor || role == AccountRole.Patient -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.account),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(R.color.darkPurple)
                )
                Spacer(Modifier.height(32.dp))
                Button(
                    onClick = onLogout,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.darkPurple),
                        contentColor = colorResource(R.color.white)
                    )
                ) {
                    Text(stringResource(R.string.logout), fontSize = 18.sp)
                }
            }
        }
    }
}
