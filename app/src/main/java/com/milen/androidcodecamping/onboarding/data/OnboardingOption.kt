package com.milen.androidcodecamping.onboarding.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OnboardingOption (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("screens") val screens : List<Screen>
) : Parcelable