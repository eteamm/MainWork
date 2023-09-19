package com.example.mainlist.Yuri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.R
import com.example.mainlist.adapter.PositionsAdapter
import com.example.mainlist.adapter.TurnAdapter
import com.example.mainlist.data.Positions
import com.example.mainlist.data.Turn
import com.google.gson.Gson

class Activity_Mainqueue : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val myJson = """
        [{
          id: 1, 
          name: “example”, 
          groupNumber: 2391
        },
        {
          id: 2, 
          name: “example”, 
          groupNumber: 2391
        }]
        """.trimIndent()


        var gsonMainqueue = Gson()
        var responseMainqueue = gsonMainqueue?.fromJson(myJson, Array<Positions>::class.java)?.toList()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainqueue)

        val recyclerView: RecyclerView = findViewById(R.id.PositionsRec)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val turnAdapterMainqueue = PositionsAdapter(this)
        val turnListMainqueue = mutableListOf<Positions>()
        responseMainqueue?.forEach{
            var position= Positions(it.id, it.name,it.groupNumber)
            turnListMainqueue.add(0,position)
        }
        recyclerView.adapter = turnAdapterMainqueue
        turnAdapterMainqueue.setItems(turnListMainqueue)

    }


}