package com.uilover.project255.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uilover.project255.R

@Composable
fun RoleSelectionScreen(
    onDoctorClick: () -> Unit,
    onPatientClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.lightPurple))
            .statusBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.choose_role),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(R.color.darkPurple)
        )
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = onDoctorClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.darkPurple),
                contentColor = colorResource(R.color.white)
            )
        ) {
            Text(text = stringResource(R.string.im_doctor), fontSize = 18.sp)
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onPatientClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.darkPurple),
                contentColor = colorResource(R.color.white)
            )
        ) {
            Text(text = stringResource(R.string.im_patient), fontSize = 18.sp)
        }
    }
}
