package com.example.mainlist.Yuri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.MainScreen
import com.example.mainlist.QueueEditing
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
          groupNumber: 2391,
          id_user: 2
        },
        {
          id: 4, 
          name: “evsvasdf”, 
          groupNumber: 3242,
          id_user: 3
        },
        {
          id: 2, 
          name: “example”, 
          groupNumber: 2391,
          id_user: 2
        }]
        """.trimIndent()

        val logged_user_id = 4;
        val creator_user_id = 1;

        var gsonMainqueue = Gson()
        var responseMainqueue = gsonMainqueue?.fromJson(myJson, Array<Positions>::class.java)?.toList()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainqueue)

        val recyclerView: RecyclerView = findViewById(R.id.PositionsRec)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val positionsAdapter = PositionsAdapter(this)
        val positionsList = mutableListOf<Positions>()
        responseMainqueue?.forEach{
            var position= Positions(it.id, it.name,it.groupNumber, it.idUser)
            positionsList.add(0,position)
        }
        recyclerView.adapter = positionsAdapter
        positionsAdapter.setItems(positionsList)

//        onClick
//        var positionNew = Positions();
//        positionsAdapter.addPosition(positionNew)
    }

    fun ToEditTurn(view: View){
        val intent = Intent(this, QueueEditing::class.java)
        startActivity(intent)
    }
    fun ExitfromTurn(view: View){
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
    }


}