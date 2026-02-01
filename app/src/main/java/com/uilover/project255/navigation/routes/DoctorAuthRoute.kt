package com.uilover.project255.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uilover.project255.feature.auth.DoctorLoginScreen
import com.uilover.project255.feature.auth.DoctorSignUpScreen
import com.uilover.project255.navigation.Screen
import com.uilover.project255.core.ViewModel.AuthViewModel
import com.uilover.project255.core.ViewModel.DoctorSignUpViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

fun NavGraphBuilder.doctorLoginRoute(
    nav: NavHostController,
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit
) {
    composable(Screen.DoctorLogin.route) {
        DoctorLoginScreen(
            authViewModel = authViewModel,
            onLoginSuccess = onLoginSuccess,
            onSignUpClick = { nav.navigate(Screen.DoctorSignUp.route) },
            onBack = { nav.popBackStack() }
        )
    }
}

fun NavGraphBuilder.doctorSignUpRoute(
    nav: NavHostController,
    onSignUpSuccess: () -> Unit
) {
    composable(Screen.DoctorSignUp.route) {
        val viewModel: DoctorSignUpViewModel = viewModel()
        DoctorSignUpScreen(
            viewModel = viewModel,
            onSuccess = onSignUpSuccess,
            onBack = { nav.popBackStack() }
        )
    }
}
