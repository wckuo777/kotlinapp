package com.example.kotlindemo.data

import com.example.kotlindemo.R
import com.example.kotlindemo.model.DrinksPic
import com.example.kotlindemo.model.JsonData


class Datasource {

    fun loadAffirmations(): List<DrinksPic> {
        return listOf<DrinksPic>(
            DrinksPic(R.string.a1,R.drawable.a1),
            DrinksPic(R.string.a2,R.drawable.a2),
            DrinksPic(R.string.a3,R.drawable.a3),
            DrinksPic(R.string.a4,R.drawable.a4),
            DrinksPic(R.string.a5,R.drawable.a5),
            DrinksPic(R.string.a6,R.drawable.a6),
            DrinksPic(R.string.a7,R.drawable.a7),
            DrinksPic(R.string.a8,R.drawable.a8),
            DrinksPic(R.string.a9,R.drawable.a9),
            DrinksPic(R.string.a10,R.drawable.a10),
            DrinksPic(R.string.a11,R.drawable.a11),
            DrinksPic(R.string.a12,R.drawable.a12),
            DrinksPic(R.string.a13,R.drawable.a13),
            DrinksPic(R.string.a14,R.drawable.a14)
        )
    }


}