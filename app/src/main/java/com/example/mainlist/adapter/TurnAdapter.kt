package com.example.mainlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.MainScreen
import com.example.mainlist.R
import com.example.mainlist.data.Turn


public class TurnAdapter(private val context: Context) : RecyclerView.Adapter<TurnAdapter.turnHolder>() {
    private var turnList = ArrayList<Turn>();
    private var Type : Boolean = false;

    class turnHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.turnNameElement);
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): turnHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.turn, parent, false)
        return turnHolder(view) //возвращает элементы для списка
    }

    override fun getItemCount(): Int {
        return turnList.size //возвращает кол-во элементов
    }

    override fun onBindViewHolder(holder: turnHolder, position: Int) {
        val turn : Turn = turnList[position] //заполнение данных в эл списка
        holder.nameTextView.text = turn.turnName
    }

    fun addTurn(turn: Turn){
        turnList.add(turn)
        notifyDataSetChanged()
    }

    fun setItems(item: MutableList<Turn>, type: Boolean) {
        Type = type;
        turnList.clear()
        turnList.addAll(item)
        notifyDataSetChanged()
    }
}