package com.uilover.project255.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.uilover.project255.core.ViewModel.MainViewModel
import com.uilover.project255.navigation.routes.detailRoute
import com.uilover.project255.navigation.routes.homeRoute
import com.uilover.project255.navigation.routes.introRoute
import com.uilover.project255.navigation.routes.topDoctorsRoute

@Composable
fun AppNavGraph(
    nav: NavHostController,
    vm: MainViewModel
) {
    NavHost(navController = nav, startDestination = Screen.Intro.route) {
        introRoute(
            onStart = {
                nav.navigate(Screen.Home.route) {
                    popUpTo(Screen.Intro.route) { inclusive = true }
                }
            }
        )

        homeRoute(
            vm = vm,
            onOpenDetail = { doctorModel -> nav.navigateToDetail(doctorModel) },
            onOpenTopDoctors = { nav.navigate(Screen.TopDoctors.route) }
        )

        topDoctorsRoute(
            vm = vm,
            { nav.popBackStack() },
            onOpenDtail = { doctorModel -> nav.navigateToDetail(doctorModel) }
        )

        detailRoute(
            nav = nav,
            onBack = { nav.popBackStack() }
        )
    }
}