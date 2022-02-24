package com.example.kotlindemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.model.DataGroup
import com.example.kotlindemo.ui.gallery.GalleryViewModel

// using listView :baseAdapter is not good solution ; recycle is better
class OrderListAdapter(private val context: Context, private val dataList: ArrayList<DataGroup>, private val onItemClicked: (DataGroup) -> Unit): RecyclerView.Adapter<OrderListAdapter.MyHolder>() {

    class MyHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
         val listText: TextView = itemView.findViewById(R.id.txtName)
         val btn: Button =itemView.findViewById(R.id.delBtn)
        val priceText: TextView = itemView.findViewById((R.id.textPrice))
        val sizeText: TextView = itemView.findViewById(R.id.textSize)
    }







    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val viewLayout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_order, parent, false)

        return MyHolder(viewLayout)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val dataItem = dataList[position]
        holder.listText.text = dataItem.name
        holder.priceText.text = dataItem.price.toString()
        holder.sizeText.text = dataItem.size
        holder.btn.setOnClickListener {

            this.removeItem(position, dataList[position])
            //Toast.makeText(context, dataList[position].toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private fun removeItem(position: Int , deldata: DataGroup){
        if(dataList.size > 0){
            dataList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,dataList.size)
            notifyDataSetChanged()
            onItemClicked(deldata)
        }
    }


}

