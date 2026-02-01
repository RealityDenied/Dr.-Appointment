package com.uilover.project255.navigation.routes

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.uilover.project255.core.ViewModel.MainViewModel
import com.uilover.project255.core.model.DoctorModel
import com.uilover.project255.feature.specialization.DoctorsBySpecializationScreen
import com.uilover.project255.navigation.Screen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun NavGraphBuilder.doctorsBySpecializationRoute(
    vm: MainViewModel,
    onBack: () -> Unit,
    onDoctorClick: (DoctorModel) -> Unit
) {
    composable(
        route = Screen.DoctorsBySpecialization.route,
        arguments = listOf(navArgument("specialization") { type = NavType.StringType })
    ) { backStackEntry ->
        val encoded = backStackEntry.arguments?.getString("specialization") ?: ""
        val specializationName = URLDecoder.decode(encoded, StandardCharsets.UTF_8.toString())
        val doctors by vm.doctors.observeAsState(emptyList())
        LaunchedEffect(Unit) { if (doctors.isEmpty()) vm.loadDoctors() }
        val filtered = doctors.filter { it.Special.contains(specializationName) }
        DoctorsBySpecializationScreen(
            specializationName = specializationName,
            doctors = filtered,
            onBack = onBack,
            onDoctorClick = onDoctorClick
        )
    }
}

fun routeDoctorsBySpecialization(specialization: String): String =
    "doctorsBySpecialization/${URLEncoder.encode(specialization, StandardCharsets.UTF_8.toString())}"
