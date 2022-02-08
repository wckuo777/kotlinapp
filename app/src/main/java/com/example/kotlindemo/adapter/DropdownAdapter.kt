package com.example.kotlindemo.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.kotlindemo.model.SpinnerItem

class DropdownAdapter(context: Context, resource: Int, objects: ArrayList<SpinnerItem>, selected: Int): ArrayAdapter<SpinnerItem>(context, resource, objects) {
    private val selectedId = selected
    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val view:TextView = super.getDropDownView(
            position,
            convertView,
            parent
        ) as TextView

        // set item text bold and sans serif font
        view.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)

        if (position == 0){
            // set the spinner disabled item text color
            view.setTextColor(Color.LTGRAY)
        }

        // set selected item style
        if (position == selectedId){
            view.background = ColorDrawable(Color.parseColor("#07B2F9"))
        }

        return view
    }
}


