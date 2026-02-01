package com.uilover.project255.core.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.uilover.project255.core.model.AppointmentModel

class AppointmentViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()

    private val _patientAppointments = MutableLiveData<List<AppointmentModel>>(emptyList())
    val patientAppointments: LiveData<List<AppointmentModel>> = _patientAppointments

    private val _doctorAppointments = MutableLiveData<List<AppointmentModel>>(emptyList())
    val doctorAppointments: LiveData<List<AppointmentModel>> = _doctorAppointments

    fun bookAppointment(
        doctorKey: String,
        doctorName: String,
        patientUid: String,
        patientName: String,
        dateTimeMillis: Long,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (doctorKey.isBlank() || patientUid.isBlank()) {
            onError("Invalid doctor or patient")
            return
        }
        val ref = db.getReference("Appointments").push()
        val id = ref.key ?: return run { onError("Failed to create appointment") }
        val map = mapOf(
            "id" to id,
            "doctorKey" to doctorKey,
            "doctorName" to doctorName,
            "patientUid" to patientUid,
            "patientName" to patientName,
            "dateTimeMillis" to dateTimeMillis,
            "status" to "scheduled"
        )
        ref.setValue(map)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Booking failed") }
    }

    fun loadPatientAppointments(patientUid: String) {
        if (patientUid.isBlank()) {
            _patientAppointments.value = emptyList()
            return
        }
        val now = System.currentTimeMillis()
        db.getReference("Appointments")
            .orderByChild("patientUid")
            .equalTo(patientUid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<AppointmentModel>()
                    for (child in snapshot.children) {
                        val id = child.key ?: continue
                        val doctorKey = child.child("doctorKey").getValue(String::class.java) ?: ""
                        val doctorName = child.child("doctorName").getValue(String::class.java) ?: ""
                        val patientUidSnap = child.child("patientUid").getValue(String::class.java) ?: ""
                        val patientName = child.child("patientName").getValue(String::class.java) ?: ""
                        val dateTimeMillis = child.child("dateTimeMillis").getValue(Long::class.java) ?: 0L
                        val status = child.child("status").getValue(String::class.java) ?: "scheduled"
                        if (dateTimeMillis >= now) {
                            list.add(
                                AppointmentModel(
                                    id = id,
                                    doctorKey = doctorKey,
                                    doctorName = doctorName,
                                    patientUid = patientUidSnap,
                                    patientName = patientName,
                                    dateTimeMillis = dateTimeMillis,
                                    status = status
                                )
                            )
                        }
                    }
                    list.sortBy { it.dateTimeMillis }
                    _patientAppointments.value = list
                }
                override fun onCancelled(error: DatabaseError) {
                    _patientAppointments.value = emptyList()
                }
            })
    }

    fun loadDoctorAppointments(doctorKey: String) {
        if (doctorKey.isBlank()) {
            _doctorAppointments.value = emptyList()
            return
        }
        val now = System.currentTimeMillis()
        db.getReference("Appointments")
            .orderByChild("doctorKey")
            .equalTo(doctorKey)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<AppointmentModel>()
                    for (child in snapshot.children) {
                        val id = child.key ?: continue
                        val doctorKeySnap = child.child("doctorKey").getValue(String::class.java) ?: ""
                        val doctorName = child.child("doctorName").getValue(String::class.java) ?: ""
                        val patientUid = child.child("patientUid").getValue(String::class.java) ?: ""
                        val patientName = child.child("patientName").getValue(String::class.java) ?: ""
                        val dateTimeMillis = child.child("dateTimeMillis").getValue(Long::class.java) ?: 0L
                        val status = child.child("status").getValue(String::class.java) ?: "scheduled"
                        if (dateTimeMillis >= now) {
                            list.add(
                                AppointmentModel(
                                    id = id,
                                    doctorKey = doctorKeySnap,
                                    doctorName = doctorName,
                                    patientUid = patientUid,
                                    patientName = patientName,
                                    dateTimeMillis = dateTimeMillis,
                                    status = status
                                )
                            )
                        }
                    }
                    list.sortBy { it.dateTimeMillis }
                    _doctorAppointments.value = list
                }
                override fun onCancelled(error: DatabaseError) {
                    _doctorAppointments.value = emptyList()
                }
            })
    }
}
