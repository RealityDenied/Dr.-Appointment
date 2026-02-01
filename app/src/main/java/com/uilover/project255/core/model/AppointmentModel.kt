package com.uilover.project255.core.model

data class AppointmentModel(
    val id: String = "",
    val doctorKey: String = "",
    val doctorName: String = "",
    val patientUid: String = "",
    val patientName: String = "",
    val dateTimeMillis: Long = 0L,
    val status: String = "scheduled"
)
