package com.uilover.project255.core.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.uilover.project255.core.model.CategoryModel
import com.uilover.project255.core.model.DoctorModel

class MainViewModel : ViewModel() {
    private val db = FirebaseDatabase.getInstance()

    private val _category = MutableLiveData<List<CategoryModel>>(emptyList())
    private val _doctors = MutableLiveData<List<DoctorModel>>(emptyList())

    val category: LiveData<List<CategoryModel>> = _category
    val doctors: LiveData<List<DoctorModel>> = _doctors


    private var categoryLoaded = false
    private var doctorsLoaded = false

    fun loadCategory(force: Boolean = false) {
        if (categoryLoaded && !force) return

        categoryLoaded = true

        val ref = db.getReference("Category")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<CategoryModel>()
                for (child in snapshot.children) {
                    if (child.hasChildren()) {
                        child.getValue(CategoryModel::class.java)?.let { items.add(it) }
                    }
                }
                _category.value = items
            }

            override fun onCancelled(error: DatabaseError) {
                categoryLoaded = false
            }

        })
    }

    fun loadDoctors(force: Boolean = false) {
        if (doctorsLoaded && !force) return
        doctorsLoaded = true

        val ref = db.getReference("Doctors")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<DoctorModel>()
                for (child in snapshot.children) {
                    if (child.hasChildren()) {
                        child.getValue(DoctorModel::class.java)?.copy(key = child.key ?: "")?.let { items.add(it) }
                    }
                }
                _doctors.value = items
            }

            override fun onCancelled(error: DatabaseError) {
                doctorsLoaded = false
            }

        })
    }
}