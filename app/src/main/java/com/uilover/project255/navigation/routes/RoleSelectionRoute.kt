package com.uilover.project255.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uilover.project255.feature.auth.RoleSelectionScreen
import com.uilover.project255.navigation.Screen

fun NavGraphBuilder.roleSelectionRoute(
    nav: NavHostController
) {
    composable(Screen.RoleSelection.route) {
        RoleSelectionScreen(
            onDoctorClick = { nav.navigate(Screen.DoctorLogin.route) },
            onPatientClick = { nav.navigate(Screen.PatientLogin.route) }
        )
    }
}
