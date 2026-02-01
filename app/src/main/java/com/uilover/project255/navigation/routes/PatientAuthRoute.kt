package com.uilover.project255.navigation.routes

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uilover.project255.core.ViewModel.AuthViewModel
import com.uilover.project255.core.ViewModel.PatientSignUpViewModel
import com.uilover.project255.feature.auth.PatientLoginScreen
import com.uilover.project255.feature.auth.PatientSignUpScreen
import com.uilover.project255.navigation.Screen

fun NavGraphBuilder.patientLoginRoute(
    nav: NavHostController,
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit
) {
    composable(Screen.PatientLogin.route) {
        PatientLoginScreen(
            authViewModel = authViewModel,
            onLoginSuccess = onLoginSuccess,
            onSignUpClick = { nav.navigate(Screen.PatientSignUp.route) },
            onBack = { nav.popBackStack() }
        )
    }
}

fun NavGraphBuilder.patientSignUpRoute(
    nav: NavHostController,
    onSignUpSuccess: () -> Unit
) {
    composable(Screen.PatientSignUp.route) {
        val viewModel: PatientSignUpViewModel = viewModel()
        PatientSignUpScreen(
            viewModel = viewModel,
            onSuccess = onSignUpSuccess,
            onBack = { nav.popBackStack() },
            onLoginClick = { nav.popBackStack() }
        )
    }
}
