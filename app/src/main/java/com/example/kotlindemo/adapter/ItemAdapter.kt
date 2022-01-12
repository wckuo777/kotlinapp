package com.example.kotlindemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.data.Datasource
import com.example.kotlindemo.model.DrinksPic
import com.example.kotlindemo.ui.home.HomeFragment
import com.example.kotlindemo.ui.home.HomeFragmentDirections


class ItemAdapter(private val context: Context, private val dataset: List<DrinksPic>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
        val buttonGroup: RadioGroup = view.findViewById(R.id.radioGroup)



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val myDataset2 = Datasource().loadDrinkDesc()
        val item = dataset[position]
        holder.textView.text =  context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
        holder.buttonGroup.removeAllViews()
        for( i in 1 .. 3){
            val rdbtn = RadioButton(context)
            rdbtn.id = View.generateViewId()
            rdbtn.text = myDataset2[i]
            holder.buttonGroup.addView(rdbtn)
        }





            //holder.button.text = myDataset2[i]


        holder.imageView.setOnClickListener {
            val action = HomeFragmentDirections.actionNavHomeToHomeDetailFragment(homeinfo = holder.textView.text.toString())
            //it.findNavController().navigate(R.id.action_nav_home_to_homeDetailFragment)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount()= dataset.size


}


