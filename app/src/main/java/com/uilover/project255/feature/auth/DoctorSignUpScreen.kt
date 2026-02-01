package com.uilover.project255.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uilover.project255.R
import com.uilover.project255.core.ViewModel.DoctorSignUpViewModel
import com.uilover.project255.core.data.InbuiltSpecializations

private val fieldModifier = Modifier.fillMaxWidth()
private val fieldShape = RoundedCornerShape(12.dp)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DoctorSignUpScreen(
    viewModel: DoctorSignUpViewModel,
    onSuccess: () -> Unit,
    onBack: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var biography by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var picture by remember { mutableStateOf("") }
    var selectedSpecializations by remember { mutableStateOf(setOf<String>()) }
    var expriense by remember { mutableStateOf(0) }
    var location by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var patiens by remember { mutableStateOf("") }
    var site by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val errorSelectSpecialization = stringResource(R.string.error_select_specialization)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.lightPurple))
            .statusBarsPadding()
            .verticalScroll(scrollState)
            .padding(24.dp)
    ) {
        AuthTopBar(onBack = onBack)
        Spacer(Modifier.height(16.dp))
        Text(
            stringResource(R.string.doctor_profile),
            fontSize = 20.sp,
            color = colorResource(R.color.darkPurple)
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email)) },
            modifier = fieldModifier,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = fieldShape,
            colors = authFieldColors()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            modifier = fieldModifier,
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = fieldShape,
            colors = authFieldColors()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.name)) },
            modifier = fieldModifier,
            singleLine = true,
            shape = fieldShape,
            colors = authFieldColors()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text(stringResource(R.string.address)) },
            modifier = fieldModifier,
            singleLine = true,
            shape = fieldShape,
            colors = authFieldColors()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = biography,
            onValueChange = { biography = it },
            label = { Text(stringResource(R.string.biography)) },
            modifier = fieldModifier,
            maxLines = 3,
            shape = fieldShape,
            colors = authFieldColors()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = picture,
            onValueChange = { picture = it },
            label = { Text(stringResource(R.string.picture_url)) },
            modifier = fieldModifier,
            singleLine = true,
            shape = fieldShape,
            colors = authFieldColors()
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.specialty),
            fontSize = 14.sp,
            color = colorResource(R.color.darkPurple),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        FlowRow(
            modifier = fieldModifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InbuiltSpecializations.all.forEach { item ->
                val selected = item.name in selectedSpecializations
                FilterChip(
                    selected = selected,
                    onClick = {
                        selectedSpecializations = if (selected) {
                            selectedSpecializations - item.name
                        } else {
                            selectedSpecializations + item.name
                        }
                    },
                    label = { Text(item.name) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = colorResource(R.color.lightPurple),
                        selectedLabelColor = colorResource(R.color.darkPurple),
                        containerColor = colorResource(R.color.white),
                        labelColor = colorResource(R.color.darkPurple)
                    )
                )
            }
        }
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = expriense.toString().takeIf { it != "0" } ?: "",
            onValueChange = { expriense = it.toIntOrNull() ?: 0 },
            label = { Text(stringResource(R.string.experience_years)) },
            modifier = fieldModifier,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = fieldShape,
            colors = authFieldColors()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text(stringResource(R.string.location)) },
            modifier = fieldModifier,
            singleLine = true,
            shape = fieldShape,
            colors = authFieldColors()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = mobile,
            onValueChange = { mobile = it },
            label = { Text(stringResource(R.string.mobile)) },
            modifier = fieldModifier,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = fieldShape,
            colors = authFieldColors()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = patiens,
            onValueChange = { patiens = it },
            label = { Text(stringResource(R.string.patients_count)) },
            modifier = fieldModifier,
            singleLine = true,
            shape = fieldShape,
            colors = authFieldColors()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = site,
            onValueChange = { site = it },
            label = { Text(stringResource(R.string.website_optional)) },
            modifier = fieldModifier,
            singleLine = true,
            shape = fieldShape,
            colors = authFieldColors()
        )
        if (error != null) {
            Spacer(Modifier.height(8.dp))
            Text(text = error!!, color = colorResource(R.color.purple_700), fontSize = 14.sp)
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                focusManager.clearFocus()
                error = null
                if (selectedSpecializations.isEmpty()) {
                    error = errorSelectSpecialization
                } else {
                    viewModel.registerDoctor(
                    email = email,
                    password = password,
                    address = address,
                    biography = biography,
                    name = name,
                    picture = picture,
                    special = InbuiltSpecializations.toSpecialString(selectedSpecializations.toList()),
                    expriense = expriense,
                    location = location,
                    mobile = mobile,
                    patiens = patiens,
                    site = site,
                    onSuccess = onSuccess,
                    onError = { error = it }
                )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.darkPurple),
                contentColor = colorResource(R.color.white)
            )
        ) {
            Text(stringResource(R.string.sign_up), fontSize = 18.sp)
        }
        Spacer(Modifier.height(32.dp))
    }
}

@Composable
private fun authFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = colorResource(R.color.darkPurple),
    focusedLabelColor = colorResource(R.color.darkPurple),
    cursorColor = colorResource(R.color.darkPurple),
    focusedTextColor = colorResource(R.color.black)
)
