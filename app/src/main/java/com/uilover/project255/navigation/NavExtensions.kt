package com.uilover.project255.navigation

import androidx.navigation.NavController
import com.uilover.project255.core.model.DoctorModel

fun NavController.navigateToDetail(doctor: DoctorModel){
    currentBackStackEntry?.savedStateHandle?.set("doctor",doctor)
    navigate(Screen.Detail.route)
}