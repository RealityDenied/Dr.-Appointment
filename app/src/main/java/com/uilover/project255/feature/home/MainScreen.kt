package com.uilover.project255.feature.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uilover.project255.R
import com.uilover.project255.core.ViewModel.AccountRole
import com.uilover.project255.core.ViewModel.AccountViewModel
import com.uilover.project255.core.ViewModel.AppointmentViewModel
import com.uilover.project255.core.ViewModel.MainViewModel
import com.uilover.project255.core.data.InbuiltSpecializations
import com.uilover.project255.core.model.DoctorModel


@Composable
fun MainScreen(
    viewModel: MainViewModel,
    accountViewModel: AccountViewModel,
    appointmentViewModel: AppointmentViewModel,
    onOpenTopDoctors: () -> Unit,
    onOpenDoctorDetail: (DoctorModel) -> Unit,
    onSpecializationClick: (String) -> Unit,
    onLogout: () -> Unit
) {
    val doctors by viewModel.doctors.observeAsState(initial = emptyList())
    val role by accountViewModel.role.observeAsState(AccountRole.None)
    val doctorProfile by accountViewModel.doctorProfile.observeAsState(null)
    val patientProfile by accountViewModel.patientProfile.observeAsState(null)

    LaunchedEffect(Unit) {
        if (doctors.isEmpty()) viewModel.loadDoctors()
        accountViewModel.loadProfile()
    }

    val displayName = when (role) {
        AccountRole.Doctor -> (doctorProfile?.Name)?.takeIf { it.isNotBlank() } ?: stringResource(R.string.new_user)
        AccountRole.Patient -> (patientProfile?.name)?.takeIf { it.isNotBlank() } ?: stringResource(R.string.new_user)
        else -> stringResource(R.string.new_user)
    }

    var selectedBottom by remember { mutableStateOf(0) }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            HomeBottomBar(
                selected = selectedBottom,
                onSelect = { selectedBottom = it }
            )
        }
    ) { inner ->
        when (selectedBottom) {
            3 -> AccountContent(viewModel = accountViewModel, appointmentViewModel = appointmentViewModel, onLogout = onLogout, onBackToHome = { selectedBottom = 0 })
            else -> LazyColumn(contentPadding = inner) {
                item { HomeHeader(userName = displayName) }
                item { Banner() }
                item { SectionHeader(title = "Doctor Speciality", onSeeAll = null) }
                item { CategoryRow(items = InbuiltSpecializations.all, onSpecializationClick = onSpecializationClick) }
                item { SectionHeader(title = "Top Doctors", onSeeAll = onOpenTopDoctors) }
                item { DoctorRow(items = doctors, onOpenDoctorDetail) }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    val viewModel: MainViewModel = viewModel()
    MainScreen(viewModel = viewModel, accountViewModel = viewModel(), appointmentViewModel = viewModel(), onOpenTopDoctors = {}, onOpenDoctorDetail = {}, onSpecializationClick = {}, onLogout = {})
}

