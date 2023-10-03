package com.example.mainlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.Yuri.Activity_Mainqueue
import com.example.mainlist.Yuri.Activity_queue_create
import com.example.mainlist.adapter.TurnAdapter
import com.example.mainlist.data.Turn
import com.google.gson.Gson

class MainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val myJson = """
        [{
          turnName: "MY TURNS!!!!!!",
          turnDesc: "Конкретно четко"
        },
        {
          turnName: "Eptaaaaaa",
          turnDesc: "вау"
        }]
        """.trimIndent()

        val dostupJson = """
        [{
          turnName: "TURNS IN DOSTUP!!!!!",
          turnDesc: "Конкретно четко"
        },
        {
          turnName: "UntiEptaaaaaa",
          turnDesc: "вау"
        }]
        """.trimIndent()

        var gson = Gson()
        var MyTurns = gson?.fromJson(myJson, Array<Turn>::class.java)?.toList()
        var InDostupTurns = gson?.fromJson(dostupJson, Array<Turn>::class.java)?.toList()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainscreen)
        val bcreateturn = findViewById<Button>(R.id.CreateTurnBtn)
        val MyTurnsBtn = findViewById<Button>(R.id.bMy)
        val InDostupBtn = findViewById<Button>(R.id.MainScreenInDostupBtn)

        MyTurnsBtn.setOnClickListener {
            val intent = Intent(this, ListOfParticipants::class.java)
            startActivity(intent);
        }

        InDostupBtn.setOnClickListener {
            val intent = Intent(this, ListOfParticipants::class.java)
            startActivity(intent);
        }

        val bExit = findViewById<ImageButton>(R.id.exitBtn)
        bExit.setOnClickListener {
            val intent = Intent(this, EntryScreen::class.java)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.turnsRec)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val turnAdapter = TurnAdapter(this)
        recyclerView.adapter = turnAdapter
        val turnList = mutableListOf<Turn>()
        bcreateturn.setOnClickListener {
            val intent = Intent(this, Activity_queue_create::class.java)
            startActivity(intent);
        }
        MyTurnsBtn.setOnClickListener {
            turnList.clear()
            MyTurns?.forEach {
                var turn = Turn(it.turnName, it.turnDesc)
                turnList.add(0, turn)
            }
            turnAdapter.setItems(turnList, true)
        }

        InDostupBtn.setOnClickListener {
            turnList.clear()
            InDostupTurns?.forEach{
                var turn = Turn(it.turnName, it.turnDesc)
                turnList.add(0,turn)
            }
            turnAdapter.setItems(turnList, false)
        }


    }


    fun CreateTurn(view : View){
        val intent = Intent(this, Activity_Mainqueue::class.java)
        startActivity(intent)
    }



}