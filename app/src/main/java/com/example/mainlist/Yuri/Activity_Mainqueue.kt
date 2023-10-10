package com.example.mainlist.Yuri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.ListOfParticipants
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
          name: "Глоба Валерия Владимировна", 
          groupNumber: 2391,
          id_user: 1
        },
        {
          id: 4, 
          name: "Ненарокова Маргарита Олеговна", 
          groupNumber: 3242,
          id_user: 3
        },
        {
          id: 2, 
          name: "Васильев Андрей Антонович", 
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
        val ButtonToPeople:Button = findViewById(R.id.turnPeopleBtn)
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


        val JoinBtn : Button = findViewById(R.id.createTurnBtn)

        JoinBtn.setOnClickListener() {
            var positionNew = Positions(9, "Yuri", "2391", 1) // idUser для каждого пользователя свой
            positionsAdapter.addPosition(positionNew)

        }
        ButtonToPeople.setOnClickListener() {
            val intent = Intent(this, ListOfParticipants::class.java)
            startActivity(intent);

        }
        val goback : ImageView = findViewById(R.id.backTurnImageView)
        goback.setOnClickListener(){
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
            finish()
        }
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