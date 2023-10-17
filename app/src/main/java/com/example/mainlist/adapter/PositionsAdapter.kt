package com.example.mainlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.R
import com.example.mainlist.data.Positions


public class PositionsAdapter(private val context: Context) : RecyclerView.Adapter<PositionsAdapter.HolderPositions>() {

    private var ListPositions = ArrayList<Positions>();
    private var

    class HolderPositions(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val UserNameTextView: TextView = itemView.findViewById(R.id.positionNameTxt)
        val UserGroupTextView: TextView = itemView.findViewById(R.id.positionNumberTxt)
        val Deletedtn = itemView.findViewById<ImageButton>(R.id.deletePositionImgBtn)
        val numberTextView = itemView.findViewById<TextView>(R.id.positionIdTxt)

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
        var i = position+1
        var Count = 0;
        holder.numberTextView.text="#"+i;
        holder.UserGroupTextView.text = positions.groupNumber
        holder.Deletedtn.setOnClickListener(){
            val deleted = ListPositions.removeAt(position)
            notifyDataSetChanged()
        }

    }



    fun addPosition(position: Positions) : Int{
        var count = ListPositions.size
        var last = 0
        var Type = -1
            for(i in 0..count-1){
                if (ListPositions[i].idUser==position.idUser) {
                    last = i + 1
                    Type = 0
                }
        }
        last = count - last
        if (last > 20 || Type != 0){
            ListPositions.add(position)
            notifyDataSetChanged()
            return 1
        }
        return 0
    }

    fun setItems(item: MutableList<Positions>) {
        ListPositions.clear()
        ListPositions.addAll(item)
        notifyDataSetChanged()
    }
}