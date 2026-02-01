package com.uilover.project255.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uilover.project255.feature.intro.IntroScreen
import com.uilover.project255.navigation.Screen
import com.uilover.project255.core.ViewModel.AuthViewModel

fun NavGraphBuilder.introRoute(
    nav: NavHostController,
    authViewModel: AuthViewModel,
    onStart: () -> Unit
) {
    composable(Screen.Intro.route) {
        IntroScreen(onStartClick = onStart)
    }
}