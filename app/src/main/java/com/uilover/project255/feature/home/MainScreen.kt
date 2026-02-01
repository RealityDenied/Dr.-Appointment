package com.uilover.project255.feature.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uilover.project255.core.ViewModel.MainViewModel
import com.uilover.project255.core.model.DoctorModel


@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onOpenTopDoctors: () -> Unit, onOpenDoctorDetail: (DoctorModel) -> Unit
) {

    val categories by viewModel.category.observeAsState(initial = emptyList())
    val doctors by viewModel.doctors.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        if (categories.isEmpty()) viewModel.loadCategory()
        if (doctors.isEmpty()) viewModel.loadDoctors()
    }

    var selectedBottom by remember { mutableStateOf(0) }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            HomeBottomBar(
                selected = selectedBottom,
                onSelect = { selectedBottom = it }
            )
        }
    ) { inner ->
        LazyColumn(contentPadding = inner) {
            item { HomeHeader() }
            item { Banner() }
            item { SectionHeader(title = "Doctor Speciality", onSeeAll = null) }
            item { CategoryRow(items = categories, onClick = {}) }
            item { SectionHeader(title = "Top Doctors", onSeeAll = onOpenTopDoctors) }
            item { DoctorRow(items = doctors, onOpenDoctorDetail) }
        }

    }
}

@Preview
@Composable
fun MainScreenPreview() {
    val viewModel: MainViewModel = viewModel()
    MainScreen(viewModel = viewModel, onOpenTopDoctors = {}, onOpenDoctorDetail = {})
}

