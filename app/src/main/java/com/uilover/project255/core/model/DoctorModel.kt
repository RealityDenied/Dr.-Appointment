package com.uilover.project255.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorModel(
    val Address: String="",
    val Biography: String="",
    val Id:Int=0,
    val Name: String="",
    val Picture: String="",
    val Special: String="",
    val Expriense: Int=0,
    val Location : String="",
    val Mobile: String="",
    val Patiens: String="",
    val Rating: Double=0.0,
    val Site: String=""
): Parcelable
