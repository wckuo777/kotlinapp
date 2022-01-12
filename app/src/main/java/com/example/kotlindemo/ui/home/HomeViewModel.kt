package com.example.kotlindemo.ui.home

import android.view.Menu
import androidx.appcompat.view.menu.MenuBuilder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment2"
    }
    val text: LiveData<String> = _text

    // radio checkedData
    private val _checkedData = MutableLiveData<List<Boolean>>()
    val checkedData: LiveData<List<Boolean>> = _checkedData


}