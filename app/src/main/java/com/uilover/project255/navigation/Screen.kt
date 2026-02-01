package com.uilover.project255.navigation

sealed class Screen(val route: String) {
    data object Intro : Screen("intro")
    data object RoleSelection : Screen("roleSelection")
    data object DoctorLogin : Screen("doctorLogin")
    data object DoctorSignUp : Screen("doctorSignUp")
    data object PatientLogin : Screen("patientLogin")
    data object PatientSignUp : Screen("patientSignUp")
    data object Home : Screen("home")
    data object TopDoctors : Screen("topDoctors")
    data object DoctorsBySpecialization : Screen("doctorsBySpecialization/{specialization}")
    data object Detail : Screen("detail")
}