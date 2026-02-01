package com.uilover.project255

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.uilover.project255.core.ViewModel.AccountViewModel
import com.uilover.project255.core.ViewModel.AppointmentViewModel
import com.uilover.project255.core.ViewModel.AuthViewModel
import com.uilover.project255.core.ViewModel.MainViewModel
import com.uilover.project255.navigation.AppNavGraph

class MainActivity : ComponentActivity() {

    private val vm by viewModels<MainViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()
    private val accountViewModel by viewModels<AccountViewModel>()
    private val appointmentViewModel by viewModels<AppointmentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val nav = rememberNavController()
            AppNavGraph(
                nav = nav,
                vm = vm,
                authViewModel = authViewModel,
                accountViewModel = accountViewModel,
                appointmentViewModel = appointmentViewModel
            )
        }
    }
}
