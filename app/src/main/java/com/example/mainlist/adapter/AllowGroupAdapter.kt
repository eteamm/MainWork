package com.example.mainlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.R
import com.example.mainlist.data.AllowGroup
import com.example.mainlist.data.Positions

class AllowGroupAdapter(private val context: Context) : RecyclerView.Adapter<AllowGroupAdapter.allowGroupHolder>(){
    private var allowGroupList = ArrayList<AllowGroup>()
    class allowGroupHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val TextGroup : TextView = itemView.findViewById(R.id.group)
        val CancelImage : ImageView = itemView.findViewById(R.id.cancel)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): allowGroupHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.allow_group, parent, false)
        return allowGroupHolder(view) //возвращает элементы для списка
    }

    override fun onBindViewHolder(holder: allowGroupHolder, position: Int) {
        val allowGroup : AllowGroup = allowGroupList[position]
        holder.TextGroup.text = allowGroup.number.toString()
    }

    override fun getItemCount(): Int {
        return 0
    }

    fun setItems(items: MutableList<AllowGroup>){
        allowGroupList.clear()
        allowGroupList.addAll(items)
        notifyDataSetChanged()
    }
}