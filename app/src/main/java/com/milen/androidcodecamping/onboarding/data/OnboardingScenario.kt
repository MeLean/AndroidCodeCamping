package com.milen.androidcodecamping.onboarding.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OnboardingScenario (

    @SerializedName("appName") val appName : String,

    @SerializedName("onboardingOptions")
    val onboardingOptions : List<OnboardingOption>
): Parcelable