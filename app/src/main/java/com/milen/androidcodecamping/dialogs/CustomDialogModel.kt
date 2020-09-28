package com.milen.androidcodecamping.dialogs

import android.os.Parcelable
import android.view.View
import kotlinx.android.parcel.Parcelize


@Parcelize
class CustomDialogModel(
    val dialogTitle: String? = null,
    val dialogText: String? = null,
    val dialogBackgroundAnimationUrl: String? = null,
    val dialogLogoAnimationUrl: String? = null,
    //TODO USE EVENT BUSS CLASSES
    val centerButton: Pair<String, View.OnClickListener>? = null,
    val leftButton: Pair<String, View.OnClickListener>? = null,
    val rightButton: Pair<String, View.OnClickListener>? = null,
) : Parcelable {
//    enum class Button {
//        LEFT, CENTER, RIGHT
//    }
}
