package com.uilover.project255.navigation.routes

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uilover.project255.core.ViewModel.MainViewModel
import com.uilover.project255.core.model.DoctorModel
import com.uilover.project255.feature.topdoctors.TopDoctorsScreen
import com.uilover.project255.navigation.Screen

fun NavGraphBuilder.topDoctorsRoute(
    vm: MainViewModel,
    obBack: () -> Unit,
    onOpenDtail: (DoctorModel) -> Unit
) {
    composable(Screen.TopDoctors.route) {
        val doctors by vm.doctors.observeAsState(emptyList())
        LaunchedEffect(Unit) { if (doctors.isEmpty()) vm.loadDoctors() }

        TopDoctorsScreen(
            doctors=doctors,
            onBack = obBack,
            onOpenDtail
        )
    }
}