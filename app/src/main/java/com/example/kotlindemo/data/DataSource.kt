package com.example.kotlindemo.data

import com.example.kotlindemo.R
import com.example.kotlindemo.model.DrinksPic



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

    fun loadDrinkDesc(): MutableMap<Int,String>{
        return mutableMapOf(1 to "not bad",
            2 to "good",
            3 to "excellent"
            )
    }

    fun loadSpinnerItem(): LinkedHashMap<Int,String>{
        val linkedHashMap = LinkedHashMap<Int, String>()
        linkedHashMap.put(0,"select drink")
        linkedHashMap.put(1,"a1 drink")
        linkedHashMap.put(2,"a2 drink")
        linkedHashMap.put(3,"a3 drink")
        linkedHashMap.put(4,"a4 drink")
        linkedHashMap.put(5,"a5 drink")
        linkedHashMap.put(6,"a6 drink")
        linkedHashMap.put(7,"a7 drink")
        linkedHashMap.put(8,"a8 drink")
        linkedHashMap.put(9,"a9 drink")
        linkedHashMap.put(10,"a10 drink")
        linkedHashMap.put(11,"a11 drink")
        linkedHashMap.put(12,"a12 drink")
        linkedHashMap.put(13,"a13 drink")
        return linkedHashMap
    }

    fun loadSpinnerItemSize(): LinkedHashMap<Int,String>{
        val linkedHashMap = LinkedHashMap<Int, String>()
        linkedHashMap.put(0,"select size")
        linkedHashMap.put(1,"M")
        linkedHashMap.put(2,"L")
        return linkedHashMap
    }


}