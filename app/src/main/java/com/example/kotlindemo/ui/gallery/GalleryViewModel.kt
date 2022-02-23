package com.example.kotlindemo.ui.gallery


import android.R.attr.data
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlindemo.model.DataGroup
import com.google.android.material.snackbar.Snackbar


class GalleryViewModel : ViewModel() {




    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text



    private val _orderData = MutableLiveData<ArrayList<DataGroup>>()
    val orderData: LiveData<ArrayList<DataGroup>> = _orderData


    private val _summary = MutableLiveData<String>()
    val summary: LiveData<String> = _summary




    init {
        _orderData.value = arrayListOf()

    }

    fun setOrderData(rData: ArrayList<DataGroup>) {

        _orderData.value = rData

        val groupData = rData.groupBy( keySelector = {it.name + " "+ it.size}, valueTransform = {it.price})
        val summaryData: Map<String, Int> = groupData.mapValues { (name, works) -> works.sumOf{ it } }
        var first = true
        val builder = StringBuilder()

        for (entry in summaryData) {
            if (first) {
                first = false
            } else {
                builder.append("\n") // Or whatever break you want
            }
            builder.append(entry.key)
                .append(": ")
                .append(entry.value)
        }
        _summary.value = builder.toString()


        Log.d("sum", builder.toString())

        Log.d("abctest", _orderData.value!![0].name)
    }

    fun sendOrderTest(view: View){

        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               .setAction("Action", null).show()
    }
}