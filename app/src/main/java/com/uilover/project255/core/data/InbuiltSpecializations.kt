package com.uilover.project255.core.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Inbuilt list of doctor specializations shown on Home and used in doctor sign-up.
 */
data class SpecializationItem(
    val id: Int,
    val name: String,
    val icon: ImageVector
)

object InbuiltSpecializations {
    val all: List<SpecializationItem> = listOf(
        SpecializationItem(1, "Cardiology", Icons.Filled.Favorite),
        SpecializationItem(2, "Dentistry", Icons.Filled.Badge),
        SpecializationItem(3, "Neurology", Icons.Filled.Psychology),
        SpecializationItem(4, "Orthopedics", Icons.Filled.AccessibilityNew),
        SpecializationItem(5, "Pediatrics", Icons.Filled.ChildCare),
        SpecializationItem(6, "OB/GYN", Icons.Filled.Female),
        SpecializationItem(7, "Physician", Icons.Filled.MedicalServices)
    )

    fun names(): List<String> = all.map { it.name }

    /** Comma-separated string for Firebase "Special" field. */
    fun toSpecialString(selectedNames: List<String>): String =
        selectedNames.joinToString(", ")
}
