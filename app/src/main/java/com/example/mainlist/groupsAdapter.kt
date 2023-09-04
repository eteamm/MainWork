package com.example.mainlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.databinding.GroupItemBinding

class groupsAdapter: RecyclerView.Adapter<groupsAdapter.groupsHolder>() {
    val groupsList = ArrayList<group>()
    class groupsHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = GroupItemBinding.bind(item)
        fun bind(groups: group) = with(binding){
            group.text = groups.groups
            cancel.setImageResource(groups.imageId)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): groupsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
        return groupsHolder(view)
    }

    override fun getItemCount(): Int {
        return groupsList.size
    }

    override fun onBindViewHolder(holder: groupsHolder, position: Int) {
        holder.bind(groupsList[position])
    }

    fun addGroup(groups: group){
        groupsList.add(groups)
        notifyDataSetChanged()
    }



}