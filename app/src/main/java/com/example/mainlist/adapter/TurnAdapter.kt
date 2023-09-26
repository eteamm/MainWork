package com.example.mainlist.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.MainScreen
import com.example.mainlist.R
import com.example.mainlist.Yuri.Activity_Mainqueue
import com.example.mainlist.Yuri.Activity_queue_create
import com.example.mainlist.data.Turn


public class TurnAdapter(private val context: Context) : RecyclerView.Adapter<TurnAdapter.turnHolder>() {
    private var turnList = ArrayList<Turn>();
    private var Type : Boolean = false;

    class turnHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.turnNameElement)
        val ButtonTextView: Button = itemView.findViewById(R.id.JoinBtn)
        val Description : TextView = itemView.findViewById(R.id.turnDescElement)
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
        if (Type){
            holder.ButtonTextView.visibility = View.GONE
            holder.Description.visibility = View.GONE
        }
        else{
            holder.ButtonTextView.visibility = View.VISIBLE
            holder.Description.visibility = View.VISIBLE
            holder.ButtonTextView.setOnClickListener(){
                val intent = Intent(context, Activity_Mainqueue::class.java)
                context.startActivity(intent)
            }

        }
    }

    fun addTurn(turn: Turn){
        turnList.add(turn)
        notifyDataSetChanged()
    }

    fun setItems(item: MutableList<Turn>, type: Boolean) {
        Type = type
        turnList.clear()
        turnList.addAll(item)
        notifyDataSetChanged()
    }
}