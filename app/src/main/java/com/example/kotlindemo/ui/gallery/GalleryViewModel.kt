package com.example.kotlindemo.ui.gallery


import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.example.kotlindemo.model.DataGroup as DataGroup
import kotlin.collections.arrayListOf as arrayListOf


class GalleryViewModel : ViewModel() {




    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text



    private val _orderData = MutableLiveData<ArrayList<DataGroup>>()
    val orderData: LiveData<ArrayList<DataGroup>> = _orderData


//    init {
//        _orderData.value = mutableListOf()
//    }

    fun setOrderData(rData: ArrayList<DataGroup>) {
        _orderData.value = ArrayList()
        _orderData.value = rData
        Log.d("abctest", _orderData.value!![0].name)
    }

    fun sendOrderTest(view: View){

        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               .setAction("Action", null).show()
    }
}