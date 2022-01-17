package com.example.kotlindemo.ui.home

import android.view.Menu
import androidx.appcompat.view.menu.MenuBuilder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class HomeViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment2"
//    }
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text


    private var _drinksComment: MutableLiveData<MutableMap<Int,Int>> = MutableLiveData()
    val drinksComment: LiveData<MutableMap<Int,Int>> = _drinksComment

    private val arr3:MutableMap<Int, Int> = mutableMapOf()

    init{
        cleanComment()
    }

    fun cleanComment(){
        arr3.clear()
    }


    fun setComment(ques: Int, rank: Int){
        arr3?.put(ques,rank)
        _drinksComment.value = arr3

    }

    fun settest(){
        _text.value= "mytest0"
    }


}


