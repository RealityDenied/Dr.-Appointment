package com.uilover.project255.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.uilover.project255.core.ViewModel.AccountViewModel
import com.uilover.project255.core.ViewModel.AppointmentViewModel
import com.uilover.project255.core.ViewModel.AuthViewModel
import com.uilover.project255.core.ViewModel.MainViewModel
import com.uilover.project255.navigation.routes.detailRoute
import com.uilover.project255.navigation.routes.doctorLoginRoute
import com.uilover.project255.navigation.routes.doctorsBySpecializationRoute
import com.uilover.project255.navigation.routes.routeDoctorsBySpecialization
import com.uilover.project255.navigation.routes.doctorSignUpRoute
import com.uilover.project255.navigation.routes.homeRoute
import com.uilover.project255.navigation.routes.introRoute
import com.uilover.project255.navigation.routes.patientLoginRoute
import com.uilover.project255.navigation.routes.patientSignUpRoute
import com.uilover.project255.navigation.routes.roleSelectionRoute
import com.uilover.project255.navigation.routes.topDoctorsRoute

@Composable
fun AppNavGraph(
    nav: NavHostController,
    vm: MainViewModel,
    authViewModel: AuthViewModel,
    accountViewModel: AccountViewModel,
    appointmentViewModel: AppointmentViewModel
) {
    val navigateToHomeAndClearBackStack: () -> Unit = {
        vm.loadDoctors()
        nav.navigate(Screen.Home.route) {
            popUpTo(Screen.Intro.route) { inclusive = true }
        }
    }

    NavHost(navController = nav, startDestination = Screen.Intro.route) {
        introRoute(
            nav = nav,
            authViewModel = authViewModel,
            onStart = {
                nav.navigate(Screen.RoleSelection.route) {
                    popUpTo(Screen.Intro.route) { inclusive = true }
                }
            }
        )

        roleSelectionRoute(nav = nav)

        doctorLoginRoute(
            nav = nav,
            authViewModel = authViewModel,
            onLoginSuccess = navigateToHomeAndClearBackStack
        )
        doctorSignUpRoute(
            nav = nav,
            onSignUpSuccess = {
                nav.navigate(Screen.DoctorLogin.route) {
                    popUpTo(Screen.DoctorSignUp.route) { inclusive = true }
                }
            }
        )
        patientLoginRoute(
            nav = nav,
            authViewModel = authViewModel,
            onLoginSuccess = navigateToHomeAndClearBackStack
        )
        patientSignUpRoute(
            nav = nav,
            onSignUpSuccess = {
                nav.navigate(Screen.PatientLogin.route) {
                    popUpTo(Screen.PatientSignUp.route) { inclusive = true }
                }
            }
        )

        homeRoute(
            vm = vm,
            accountViewModel = accountViewModel,
            appointmentViewModel = appointmentViewModel,
            onOpenDetail = { doctorModel -> nav.navigateToDetail(doctorModel) },
            onOpenTopDoctors = { nav.navigate(Screen.TopDoctors.route) },
            onSpecializationClick = { name -> nav.navigate(routeDoctorsBySpecialization(name)) },
            onLogout = {
                authViewModel.signOut()
                nav.navigate(Screen.Intro.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            }
        )

        topDoctorsRoute(
            vm = vm,
            { nav.popBackStack() },
            onOpenDtail = { doctorModel -> nav.navigateToDetail(doctorModel) }
        )

        doctorsBySpecializationRoute(
            vm = vm,
            onBack = { nav.popBackStack() },
            onDoctorClick = { doctorModel -> nav.navigateToDetail(doctorModel) }
        )

        detailRoute(
            nav = nav,
            accountViewModel = accountViewModel,
            appointmentViewModel = appointmentViewModel,
            onBack = { nav.popBackStack() }
        )
    }
}