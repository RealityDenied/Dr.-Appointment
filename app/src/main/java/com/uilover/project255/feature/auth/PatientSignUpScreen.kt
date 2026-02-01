package com.uilover.project255.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.uilover.project255.core.ViewModel.PatientSignUpViewModel

@Composable
fun PatientSignUpScreen(
    viewModel: PatientSignUpViewModel,
    onSuccess: () -> Unit,
    onBack: () -> Unit,
    onLoginClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.lightPurple))
            .statusBarsPadding()
            .padding(24.dp)
    ) {
        AuthTopBar(onBack = onBack)
        Spacer(Modifier.height(16.dp))
        Text(
            stringResource(R.string.patient_profile),
            fontSize = 20.sp,
            color = colorResource(R.color.darkPurple)
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.darkPurple),
                focusedLabelColor = colorResource(R.color.darkPurple),
                cursorColor = colorResource(R.color.darkPurple),
                focusedTextColor = colorResource(R.color.black)
            )
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.darkPurple),
                focusedLabelColor = colorResource(R.color.darkPurple),
                cursorColor = colorResource(R.color.darkPurple),
                focusedTextColor = colorResource(R.color.black)
            )
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.name)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.darkPurple),
                focusedLabelColor = colorResource(R.color.darkPurple),
                cursorColor = colorResource(R.color.darkPurple),
                focusedTextColor = colorResource(R.color.black)
            )
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text(stringResource(R.string.phone_number)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.darkPurple),
                focusedLabelColor = colorResource(R.color.darkPurple),
                cursorColor = colorResource(R.color.darkPurple),
                focusedTextColor = colorResource(R.color.black)
            )
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
                viewModel.registerPatient(
                    email = email,
                    password = password,
                    name = name,
                    phone = phone,
                    onSuccess = onSuccess,
                    onError = { error = it }
                )
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
        Spacer(Modifier.height(16.dp))
        TextButton(onClick = onLoginClick) {
            Text(
                stringResource(R.string.have_account),
                color = colorResource(R.color.darkPurple),
                fontSize = 14.sp
            )
        }
    }
}
