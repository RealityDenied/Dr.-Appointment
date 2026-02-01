package com.uilover.project255.core.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.uilover.project255.core.model.DoctorModel
import com.uilover.project255.core.model.PatientModel

sealed class AccountRole {
    data object None : AccountRole()
    data object Doctor : AccountRole()
    data object Patient : AccountRole()
}

class AccountViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()

    private val _role = MutableLiveData<AccountRole>(AccountRole.None)
    val role: LiveData<AccountRole> = _role

    private val _doctorProfile = MutableLiveData<DoctorModel?>(null)
    val doctorProfile: LiveData<DoctorModel?> = _doctorProfile

    private val _patientProfile = MutableLiveData<PatientModel?>(null)
    val patientProfile: LiveData<PatientModel?> = _patientProfile

    private val _doctorKey = MutableLiveData<String?>(null)
    val doctorKey: LiveData<String?> = _doctorKey

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun loadProfile() {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            _loading.value = false
            return
        }
        _loading.value = true
        db.getReference("DoctorIds").child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val key = snapshot.getValue(String::class.java)
                if (!key.isNullOrBlank()) {
                    _doctorKey.value = key
                    _role.value = AccountRole.Doctor
                    db.getReference("Doctors").child(key).addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(docSnap: DataSnapshot) {
                            _doctorProfile.value = docSnap.getValue(DoctorModel::class.java)?.copy(key = key)
                            _loading.value = false
                        }
                        override fun onCancelled(error: DatabaseError) { _loading.value = false }
                    })
                } else {
                    db.getReference("Patients").child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(patSnap: DataSnapshot) {
                            val name = patSnap.child("name").getValue(String::class.java) ?: ""
                            val phone = patSnap.child("phone").getValue(String::class.java) ?: ""
                            val email = patSnap.child("email").getValue(String::class.java) ?: auth.currentUser?.email ?: ""
                            _patientProfile.value = PatientModel(name = name, phone = phone, email = email)
                            _role.value = AccountRole.Patient
                            _loading.value = false
                        }
                        override fun onCancelled(error: DatabaseError) { _loading.value = false }
                    })
                }
            }
            override fun onCancelled(error: DatabaseError) {
                db.getReference("Patients").child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(patSnap: DataSnapshot) {
                        if (patSnap.exists()) {
                            val name = patSnap.child("name").getValue(String::class.java) ?: ""
                            val phone = patSnap.child("phone").getValue(String::class.java) ?: ""
                            val email = patSnap.child("email").getValue(String::class.java) ?: auth.currentUser?.email ?: ""
                            _patientProfile.value = PatientModel(name = name, phone = phone, email = email)
                            _role.value = AccountRole.Patient
                        }
                        _loading.value = false
                    }
                    override fun onCancelled(e: DatabaseError) { _loading.value = false }
                })
            }
        })
    }

}
