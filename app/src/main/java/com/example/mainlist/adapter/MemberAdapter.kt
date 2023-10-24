package com.example.mainlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.R
import com.example.mainlist.data.Member


public class MemberAdapter(private val context: Context) : RecyclerView.Adapter<MemberAdapter.MemberHolder>()  {
    val IDM_A = 101
    val IDM_B = 102
    private var memberList = ArrayList<Member>();

    class MemberHolder(itemView: View, c : Context) : RecyclerView.ViewHolder(itemView){
        val memberNameTextView: TextView = itemView.findViewById(R.id.memberNameTxt)
        val imageContextMenuButton: ImageButton = itemView.findViewById(R.id.pointsMember)
        val popupMenu = PopupMenu(c, imageContextMenuButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.member, parent, false)

        return MemberHolder(view, context) //возвращает элементы для списка
    }

    override fun getItemCount(): Int {
        return memberList.size //возвращает кол-во элементов
    }

    override fun onBindViewHolder(holder: MemberHolder, position: Int) {
        val member : Member = memberList[position] //заполнение данных в эл списка
        holder.memberNameTextView.text = member.Name
        val button = holder.imageContextMenuButton
        holder.popupMenu.inflate(R.menu.member_item)
//        popupMenu.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.menu1 -> {
//                    textView.text = "Вы выбрали PopupMenu 1"
//                    true
//                }
//                R.id.menu2 -> {
//                    textView.text = "Вы выбрали PopupMenu 2"
//                    true
//                }
//                R.id.menu3 -> {
//                    textView.text = "Вы выбрали PopupMenu 3"
//                    true
//                }
//                else -> false
//            }
//        }

       button.setOnClickListener {
           holder.popupMenu.show()
        }


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