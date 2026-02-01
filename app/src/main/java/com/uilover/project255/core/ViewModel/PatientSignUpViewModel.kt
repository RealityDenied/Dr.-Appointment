package com.uilover.project255.core.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class PatientSignUpViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()

    fun registerPatient(
        email: String,
        password: String,
        name: String,
        phone: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isBlank() || password.isBlank() || name.isBlank() || phone.isBlank()) {
            onError("Email, password, name and phone are required")
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val uid = it.user?.uid ?: return@addOnSuccessListener
                val patientMap = mapOf(
                    "name" to name,
                    "phone" to phone,
                    "email" to email
                )
                db.getReference("Patients").child(uid).setValue(patientMap)
                    .addOnSuccessListener {
                        db.getReference("UserRoles").child(uid).setValue("patient")
                            .addOnSuccessListener { onSuccess() }
                            .addOnFailureListener { onError(it.message ?: "Failed to save role") }
                    }
                    .addOnFailureListener { onError(it.message ?: "Failed to save patient") }
            }
            .addOnFailureListener { onError(it.message ?: "Sign up failed") }
    }
}
