package com.example.mainlist


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(private val names: List<String>) : RecyclerView
.Adapter<CustomRecyclerAdapter.MyViewHolder>() {
    var counter = 0
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val largeTextView: TextView = itemView.findViewById(R.id.textViewLarge)
        val smallTextView: TextView = itemView.findViewById(R.id.textViewSmall)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val array = arrayOfNulls<Number>(100)

        for (i in array.indices) {
            array[i] = i + 1

        }
        holder.largeTextView.text = array[counter].toString() + " Кот"
        holder.smallTextView.text = names[position]
        counter = counter + 1
    }


    override fun getItemCount() = names.size
}