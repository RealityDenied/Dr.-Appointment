package com.uilover.project255.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uilover.project255.core.ViewModel.AccountViewModel
import com.uilover.project255.core.ViewModel.AppointmentViewModel
import com.uilover.project255.core.ViewModel.MainViewModel
import com.uilover.project255.core.model.DoctorModel
import com.uilover.project255.feature.home.MainScreen
import com.uilover.project255.navigation.Screen

fun NavGraphBuilder.homeRoute(
    vm: MainViewModel,
    accountViewModel: AccountViewModel,
    appointmentViewModel: AppointmentViewModel,
    onOpenTopDoctors: () -> Unit,
    onOpenDetail: (DoctorModel) -> Unit,
    onSpecializationClick: (String) -> Unit,
    onLogout: () -> Unit
) {
    composable(Screen.Home.route) {
        MainScreen(
            viewModel = vm,
            accountViewModel = accountViewModel,
            appointmentViewModel = appointmentViewModel,
            onOpenTopDoctors = onOpenTopDoctors,
            onOpenDoctorDetail = onOpenDetail,
            onSpecializationClick = onSpecializationClick,
            onLogout = onLogout
        )
    }
}