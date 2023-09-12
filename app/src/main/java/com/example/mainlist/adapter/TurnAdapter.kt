package com.example.mainlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.R
import com.example.mainlist.data.Turn


public class TurnAdapter : RecyclerView.Adapter<TurnAdapter.turnHolder>() {
    var turnList = ArrayList<Turn>()
    fun ItemAdapter2() {
        turnList = ArrayList<Turn>()
    }

    class turnHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.turnNameElement);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): turnHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.turn, parent, false)
        return turnHolder(view) //возвращает элементы для списка
    }

    override fun getItemCount(): Int {
        return turnList.size //возвращает кол-во элементов
    }

    override fun onBindViewHolder(holder: turnHolder, position: Int) {
        val turn : Turn = turnList.get(position) //заполнение данных в эл списка
        holder.nameTextView.setText(turn.turnName)
    }

    fun addTurn(turn: Turn){
        turnList.add(turn)
        notifyDataSetChanged()
    }

    fun setItems(item: List<Turn>) {
        turnList.clear()
        turnList.addAll(item)
        notifyDataSetChanged()
    }
}