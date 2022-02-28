package com.example.kotlindemo.data

import com.example.kotlindemo.R.drawable
import com.example.kotlindemo.R.string
import com.example.kotlindemo.model.DrinksPic


class Datasource {

    fun loadAffirmations(): List<DrinksPic> {
        return listOf<DrinksPic>(
            DrinksPic(string.a1, drawable.a1),
            DrinksPic(string.a2, drawable.a2),
            DrinksPic(string.a3, drawable.a3),
            DrinksPic(string.a4, drawable.a4),
            DrinksPic(string.a5, drawable.a5),
            DrinksPic(string.a6, drawable.a6),
            DrinksPic(string.a7, drawable.a7),
            DrinksPic(string.a8, drawable.a8),
            DrinksPic(string.a9, drawable.a9),
            DrinksPic(string.a10, drawable.a10),
            DrinksPic(string.a11, drawable.a11),
            DrinksPic(string.a12, drawable.a12),
            DrinksPic(string.a13, drawable.a13),
            DrinksPic(string.a14, drawable.a14)
        )
    }

    fun loadDrinkDesc(): MutableMap<Int,String>{
        return mutableMapOf(1 to "not bad",
            2 to "good",
            3 to "excellent"
            )
    }

    fun loadSpinnerItem(): LinkedHashMap<Int, String>{
        val linkedHashMap = LinkedHashMap<Int, String>()
        linkedHashMap.put(0,"選擇項目")
        linkedHashMap.put(1,"茉莉綠茶")
        linkedHashMap.put(2,"阿薩姆紅茶")
        linkedHashMap.put(3,"四季春青茶")
        linkedHashMap.put(4,"黃金烏龍")
        linkedHashMap.put(5,"檸檬綠")
        linkedHashMap.put(6,"梅綠")
        linkedHashMap.put(7,"奶茶")
        linkedHashMap.put(8,"奶綠")
        linkedHashMap.put(9,"檸檬汁")
        linkedHashMap.put(10,"金桔檸檬")
        linkedHashMap.put(11,"檸檬梅汁")
        linkedHashMap.put(12,"檸檬養樂多")
        linkedHashMap.put(13,"冰茶")
        linkedHashMap.put(13,"紅茶拿鐵")
        return linkedHashMap
    }

    fun loadSpinnerItemSize(): LinkedHashMap<Int,String>{
        val linkedHashMap = LinkedHashMap<Int, String>()
        linkedHashMap[0] = "選擇尺寸"
        linkedHashMap[1] = "M"
        linkedHashMap[2] = "L"
        return linkedHashMap
    }


}