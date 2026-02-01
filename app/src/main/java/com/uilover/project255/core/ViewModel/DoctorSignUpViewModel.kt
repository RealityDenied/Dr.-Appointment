package com.uilover.project255.core.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DoctorSignUpViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()

    fun registerDoctor(
        email: String,
        password: String,
        address: String,
        biography: String,
        name: String,
        picture: String,
        special: String,
        expriense: Int,
        location: String,
        mobile: String,
        patiens: String,
        site: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isBlank() || password.isBlank() || name.isBlank()) {
            onError("Email, password and name are required")
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val counterRef = db.getReference("DoctorsIdCounter")
                counterRef.runTransaction(object : com.google.firebase.database.Transaction.Handler {
                    override fun doTransaction(currentData: com.google.firebase.database.MutableData): com.google.firebase.database.Transaction.Result {
                        val current = (currentData.getValue(Int::class.java) ?: 0)
                        currentData.value = current + 1
                        return com.google.firebase.database.Transaction.success(currentData)
                    }
                    override fun onComplete(error: com.google.firebase.database.DatabaseError?, committed: Boolean, currentData: com.google.firebase.database.DataSnapshot?) {
                        if (error != null || !committed) {
                            onError(error?.message ?: "Failed to generate ID")
                            return
                        }
                        val newId = (currentData?.getValue(Int::class.java) as? Number)?.toInt() ?: 1
                        val uid = auth.currentUser?.uid
                        if (uid == null) {
                            onError("User not found")
                            return
                        }
                        val doctorsRef = db.getReference("Doctors")
                        val pushRef = doctorsRef.push()
                        val doctorKey = pushRef.key
                        if (doctorKey == null) {
                            onError("Failed to create doctor reference")
                            return
                        }
                        val doctorMap = mapOf(
                            "Address" to address,
                            "Biography" to biography,
                            "Id" to newId,
                            "Name" to name,
                            "Picture" to picture,
                            "Special" to special,
                            "Expriense" to expriense,
                            "Location" to location,
                            "Mobile" to mobile,
                            "Patiens" to patiens,
                            "Rating" to 1.0,
                            "Site" to site,
                            "uid" to uid
                        )
                        pushRef.setValue(doctorMap)
                            .addOnSuccessListener {
                                db.getReference("DoctorIds").child(uid).setValue(doctorKey)
                                    .addOnSuccessListener {
                                        db.getReference("UserRoles").child(uid).setValue("doctor")
                                            .addOnSuccessListener { onSuccess() }
                                            .addOnFailureListener { onError(it.message ?: "Failed to save role") }
                                    }
                                    .addOnFailureListener { onError(it.message ?: "Failed to save doctor id") }
                            }
                            .addOnFailureListener { onError(it.message ?: "Failed to save doctor") }
                    }
                })
            }
            .addOnFailureListener { onError(it.message ?: "Sign up failed") }
    }
}
