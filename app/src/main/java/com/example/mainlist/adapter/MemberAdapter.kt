package com.example.mainlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.R
import com.example.mainlist.data.Member

public class MemberAdapter(private val context: Context) : RecyclerView.Adapter<MemberAdapter.Memberholder>()  {
    private var memberList = ArrayList<Member>();

    class Memberholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val membernameTextView: TextView = itemView.findViewById(R.id.memberNameTxt)
        val imageContextMenuButton: ImageButton = itemView.findViewById(R.id.pointsMember)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Memberholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.member, parent, false)

        return Memberholder(view) //возвращает элементы для списка
    }

    override fun getItemCount(): Int {
        return memberList.size //возвращает кол-во элементов
    }

    override fun onBindViewHolder(holder: Memberholder, position: Int) {
        val member : Member = memberList[position] //заполнение данных в эл списка
        holder.membernameTextView.text = member.Name

    }

    fun addMember(member: Member){
        memberList.add(member)
        notifyDataSetChanged()
    }

    fun setItems(item: MutableList<Member>) {
        memberList.clear()
        memberList.addAll(item)
        notifyDataSetChanged()
    }
}