package com.example.kotlindemo.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DrinksPic (@StringRes val stringResourceId: Int, @DrawableRes val imageResourceId: Int)