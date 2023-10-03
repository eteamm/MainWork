package com.example.mainlist.Yuri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.MainScreen
import com.example.mainlist.R
import com.example.mainlist.adapter.AllowGroupAdapter
import com.example.mainlist.data.AllowGroup

class Activity_queue_create : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue_create)
        val allowGroupsRec = findViewById<RecyclerView>(R.id.allowRec)
        allowGroupsRec.layoutManager = LinearLayoutManager(this)
        val allowGroupList = mutableListOf<AllowGroup>()
        val group = AllowGroup(0, 2391)
        val group2 = AllowGroup(1, 2392)
        val group3 = AllowGroup(2, 2393)
        val group4 = AllowGroup(3, 2394)
        val group5 = AllowGroup(4, 2395)
        val group6 = AllowGroup(5, 2396)
        val group7 = AllowGroup(6, 2397)
        val group8 = AllowGroup(7, 2398)
        allowGroupList.add(group)
        allowGroupList.add(group2)
        allowGroupList.add(group3)
        allowGroupList.add(group4)
        allowGroupList.add(group5)
        allowGroupList.add(group6)
        allowGroupList.add(group7)
        allowGroupList.add(group8)
        val allowgroupAdapter = AllowGroupAdapter(this)
        allowGroupsRec.adapter = allowgroupAdapter
        allowgroupAdapter.setItems(allowGroupList)

        val text = allowgroupAdapter.itemCount.toString()

        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(this, text, duration)
        toast.show()
    }



    fun ExitfromCreateTurn(view: View){
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
    }
}