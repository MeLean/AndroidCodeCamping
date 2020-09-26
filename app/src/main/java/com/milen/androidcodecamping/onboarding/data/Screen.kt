package com.milen.androidcodecamping.onboarding.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Screen  (
    @SerializedName("title") val title : String,
    @SerializedName("text") val text : String,
    @SerializedName("animationUrl") val animationUrl : String,
    @SerializedName("imageUrl") val imageUrl : String,
    @SerializedName("reminderTime") val reminderTime : String,
    @SerializedName("bottomText") val bottomText : String
): Parcelable