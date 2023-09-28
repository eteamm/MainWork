package com.example.mainlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.R
import com.example.mainlist.data.Positions


public class PositionsAdapter(private val context: Context) : RecyclerView.Adapter<PositionsAdapter.HolderPositions>() {

    private var ListPositions = ArrayList<Positions>();

    class HolderPositions(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val UserNameTextView: TextView = itemView.findViewById(R.id.positionNameTxt);
        val UserGroupTextView: TextView = itemView.findViewById(R.id.positionNumberTxt);
        val Deletedtn = itemView.findViewById<ImageButton>(R.id.deletePositionImgBtn)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionsAdapter.HolderPositions {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.position, parent, false)
        return PositionsAdapter.HolderPositions(view)
    }

    override fun getItemCount(): Int {
        return ListPositions.size
    }


    override fun onBindViewHolder(holder: HolderPositions, position: Int) {
        val positions : Positions = ListPositions[position] //заполнение данных в эл списка
        holder.UserNameTextView.text = positions.name
        holder.UserGroupTextView.text = positions.groupNumber
        holder.Deletedtn.setOnClickListener(){
            val deleted = ListPositions.removeAt(position);
            notifyDataSetChanged()
        }

    }

    fun addTurn(position: Positions){
        ListPositions.add(position)
        notifyDataSetChanged()
    }

    fun setItems(item: MutableList<Positions>) {
        ListPositions.clear()
        ListPositions.addAll(item)
        notifyDataSetChanged()
    }
}