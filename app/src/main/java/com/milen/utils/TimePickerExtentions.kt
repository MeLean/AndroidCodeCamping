package com.milen.utils

import android.os.Build
import android.widget.TimePicker


fun TimePicker.addTime(hour: Int, minute: Int) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setHour(hour)
        setMinute(minute)
    } else {
        @Suppress("DEPRECATION")
        currentHour = hour
        @Suppress("DEPRECATION")
        currentMinute = minute
    }
}