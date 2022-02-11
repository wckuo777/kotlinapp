package com.example.kotlindemo.model

import android.bluetooth.BluetoothClass
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DrinksPic (@StringRes val stringResourceId: Int, @DrawableRes val imageResourceId: Int)
//class DataModel internal constructor(var name: String?, var checked: Boolean)
data class DataGroup(val name: String)
class SpinnerItem(var id: Int, var name: String) {

    //to display object as a string in spinner
    override fun toString(): String {
        return name
    }

    override fun equals(obj: Any?): Boolean {
        if (obj is SpinnerItem) {
            val c = obj
            if (c.name == name && c.id === id) return true
        }
        return false
    }

    fun getId(): Any {
        return id;
    }

    fun getName(): Any? {
        return name;
    }
}

