package com.example.mainlist.Yuri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        allowGroupList.add(group)
        val allowgroupAdapter = AllowGroupAdapter(this)
        allowGroupsRec.adapter = allowgroupAdapter
        allowgroupAdapter.setItems(allowGroupList)
    }



    fun ExitfromCreateTurn(view: View){
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
    }
}