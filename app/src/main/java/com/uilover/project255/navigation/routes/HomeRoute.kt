package com.uilover.project255.navigation.routes

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uilover.project255.core.ViewModel.MainViewModel
import com.uilover.project255.core.model.DoctorModel
import com.uilover.project255.feature.home.MainScreen
import com.uilover.project255.navigation.Screen

fun NavGraphBuilder.homeRoute(
    vm: MainViewModel,
    onOpenTopDoctors: () -> Unit,
    onOpenDetail: (DoctorModel) -> Unit
){
    composable(Screen.Home.route) {
        val categories by vm.category.observeAsState(emptyList())
        val doctors by vm.doctors.observeAsState(emptyList())

        LaunchedEffect(Unit) {
            if(categories.isEmpty()) vm.loadCategory()
            if(doctors.isEmpty()) vm.loadDoctors()
        }

        MainScreen(viewModel=vm,
            onOpenTopDoctors=onOpenTopDoctors,
        onOpenDoctorDetail=onOpenDetail
        )
    }
}