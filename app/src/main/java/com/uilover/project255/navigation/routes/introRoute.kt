package com.uilover.project255.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uilover.project255.feature.intro.IntroScreen
import com.uilover.project255.navigation.Screen

fun NavGraphBuilder.introRoute(onStart: () -> Unit) {
    composable(Screen.Intro.route) {
        IntroScreen(onStartClick = onStart)
    }
}