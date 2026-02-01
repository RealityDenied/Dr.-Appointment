package com.uilover.project255.navigation.routes

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uilover.project255.core.ViewModel.AccountRole
import com.uilover.project255.core.ViewModel.AccountViewModel
import com.uilover.project255.core.ViewModel.AppointmentViewModel
import com.uilover.project255.core.model.DoctorModel
import com.uilover.project255.feature.detail.DetailScreen
import com.uilover.project255.navigation.Screen
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.detailRoute(
    nav: NavHostController,
    accountViewModel: AccountViewModel,
    appointmentViewModel: AppointmentViewModel,
    onBack: () -> Unit
) {
    composable(Screen.Detail.route) { backStackEntry: NavBackStackEntry ->
        val context = LocalContext.current
        val prevEntry = remember(nav) { nav.previousBackStackEntry }
        val doctor = remember(prevEntry) {
            prevEntry?.savedStateHandle?.get<DoctorModel>("doctor")
        }
        val role by accountViewModel.role.observeAsState(AccountRole.None)
        val patientProfile by accountViewModel.patientProfile.observeAsState(null)
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        var showDateTimePicker by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = System.currentTimeMillis(),
            yearRange = IntRange(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.YEAR) + 2)
        )
        val timePickerState = rememberTimePickerState(
            initialHour = 9,
            initialMinute = 0,
            is24Hour = false
        )

        LaunchedEffect(Unit) {
            accountViewModel.loadProfile()
        }
        LaunchedEffect(prevEntry, doctor) {
            if (doctor == null) {
                onBack()
            } else {
                prevEntry?.savedStateHandle?.remove<DoctorModel>("doctor")
            }
        }

        val canBook = role == AccountRole.Patient && patientProfile != null
        val patientUid = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid
        val patientName = patientProfile?.name ?: ""

        fun onMakeAppointmentClick() {
            if (canBook) {
                showDateTimePicker = true
            } else {
                scope.launch {
                    snackbarHostState.showSnackbar("Log in as a patient to book an appointment")
                }
            }
        }

        fun confirmBooking() {
            val dateMillis = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
            val calendar = Calendar.getInstance().apply {
                timeInMillis = dateMillis
                set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                set(Calendar.MINUTE, timePickerState.minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            val dateTimeMillis = calendar.timeInMillis
            if (doctor != null && patientUid != null && doctor.key.isNotBlank()) {
                appointmentViewModel.bookAppointment(
                    doctorKey = doctor.key,
                    doctorName = doctor.Name,
                    patientUid = patientUid,
                    patientName = patientName,
                    dateTimeMillis = dateTimeMillis,
                    onSuccess = {
                        showDateTimePicker = false
                        scope.launch {
                            snackbarHostState.showSnackbar("Appointment booked successfully")
                        }
                    },
                    onError = {
                        scope.launch {
                            snackbarHostState.showSnackbar(it)
                        }
                    }
                )
            }
        }

        if (doctor != null) {
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) }
            ) { _ ->
                DetailScreen(
                    item = doctor,
                    onBack = onBack,
                    canBook = canBook,
                    onMakeAppointmentClick = ::onMakeAppointmentClick,
                    onOpenWebsite = { url ->
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    },
                    onSendSms = { mobile, body ->
                        context.startActivity(
                            Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$mobile"))
                                .apply { putExtra("sms_body", body) }
                        )
                    },
                    onDial = { mobile ->
                        context.startActivity(
                            Intent(Intent.ACTION_DIAL, Uri.parse("tel:${mobile.trim()}"))
                        )
                    },
                    onDirection = { loc ->
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(loc)))
                    },
                    onShare = { subject, text ->
                        context.startActivity(
                            Intent.createChooser(
                                Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_SUBJECT, subject)
                                    putExtra(Intent.EXTRA_TEXT, text)
                                },
                                "choose one"
                            )
                        )
                    }
                )
            }

            if (showDateTimePicker) {
                AlertDialog(
                    onDismissRequest = { showDateTimePicker = false },
                    title = { Text("Select date and time") },
                    text = {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            DatePicker(
                                state = datePickerState,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(16.dp))
                            TimeInput(
                                state = timePickerState,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    confirmButton = {
                        Button(onClick = ::confirmBooking) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDateTimePicker = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        } else {
            Spacer(Modifier.height(1.dp))
        }
    }
}
