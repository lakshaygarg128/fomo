package com.example.fomo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val uId: String="",
    val firstName:String="",
    val lastName:String="",
    val email:String=""
):Parcelable