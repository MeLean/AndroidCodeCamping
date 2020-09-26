package com.milen.utils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.startIntentAndFinish(destinationClass: Class<out AppCompatActivity>) {
    val newIntent = Intent(this, destinationClass)
    startActivity(newIntent)
    finish()
}

fun AppCompatActivity.startIntentWithExtrasAndFinish(destinationClass: Class<AppCompatActivity>, extras: Bundle = Bundle()) {
    val newIntent = Intent(this, destinationClass)
    newIntent.putExtras(extras)
    startActivity(newIntent)
    finish()
}

//fun AppCompatActivity.startIntentForResult(destinationScreen: Screen, expectedResultCode: Int) {
//    val newIntent = Intent(this, destinationScreen.getDestinationClass())
//    startActivityForResult(newIntent, expectedResultCode)
//}
//
//fun AppCompatActivity.startIntentForResultWithExtras(destinationScreen: Screen, expectedResultCode: Int, extras: Bundle = Bundle()) {
//    val newIntent = Intent(this, destinationScreen.getDestinationClass())
//    newIntent.putExtras(extras)
//    startActivityForResult(newIntent, expectedResultCode)
//}

//fun AppCompatActivity.extractStringExtra(key: String): String? {
//    return intent.extras?.getString(key)
//}
//
//fun <T : Parcelable>AppCompatActivity.extractParcelableExtra(key: String): T? {
//    return intent.extras?.getParcelable(key)
//}
//
//fun AppCompatActivity.isActivityStartedForResult(): Boolean {
//    return callingActivity != null
//}

//fun AppCompatActivity.getAppDialogBuilder() : AlertDialog.Builder{
//    return AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
//}