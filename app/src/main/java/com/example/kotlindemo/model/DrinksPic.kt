package com.example.kotlindemo.model

import android.bluetooth.BluetoothClass
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DrinksPic (@StringRes val stringResourceId: Int, @DrawableRes val imageResourceId: Int)

class JsonData (
    val mobile: List<Mobile>

        )

data class Mobile (
    val id: Int,
    val device: String
)