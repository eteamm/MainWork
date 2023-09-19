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
import com.example.mainlist.adapter.TurnAdapter
import com.example.mainlist.data.Turn
import com.google.gson.Gson

class MainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val myJson = """
        [{
          turnName: "Зачетная неделя",
          turnDesc: "Конкретно четко"
        },
        {
          turnName: "Хуйня",
          turnDesc: "вау"
        }]
        """.trimIndent()
        var gson = Gson()
        var responseTurns = gson?.fromJson(myJson, Array<Turn>::class.java)?.toList()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainscreen)
        val bcreateturn = findViewById<Button>(R.id.CreateTurnBtn)
        bcreateturn.setOnClickListener {
            val intent = Intent(this, ListOfParticipants::class.java)
            startActivity(intent);
        }

        val bExit = findViewById<ImageButton>(R.id.exitBtn)
        bExit.setOnClickListener {
            val intent = Intent(this, Activity_Mainqueue::class.java)
            startActivity(intent);
        }

        val recyclerView: RecyclerView = findViewById(R.id.turnsRec)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val turnAdapter = TurnAdapter(this)
        val turnList = mutableListOf<Turn>()
        responseTurns?.forEach{
            var turn = Turn(it.turnName, it.turnDesc)
            turnList.add(0,turn)
        }
        recyclerView.adapter = turnAdapter
        turnAdapter.setItems(turnList)

    }






    fun CreateTurn(view : View){
        val intent = Intent(this, Activity_Mainqueue::class.java)
        startActivity(intent)
    }
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_mainscreen)
//    val myJson = """
//        [{
//          turnName: "Зачетная неделя",
//          turnDesc: "Конкретно четко"
//        },
//        {
//          turnName: "Хуйня",
//          turnDesc: "вау"
//        }]
//        """.trimIndent()
//    var gson = Gson()
//    var mMineUserEntity = gson?.fromJson(myJson, Array<Turn>::class.java)?.toList()
//
//    val UserName = findViewById<TextView>(R.id.userNameMain)
//    val Status = findViewById<TextView>(R.id.statusMain)
//    val Group = findViewById<TextView>(R.id.groupMain)
//    //        if (mMineUserEntity != null) {
////            UserName.text =mMineUserEntity.nickname
////        }
////        if (mMineUserEntity != null) {
////            Status.text =mMineUserEntity.workStatus
////        }
////        if (mMineUserEntity != null) {
////            Group.text =mMineUserEntity.group
////        }
//    val bcreateturn = findViewById<Button>(R.id.bCreateTurn)
//    bcreateturn.setOnClickListener {
//        val intent = Intent(this, ListOfParticipants::class.java)
//        startActivity(intent);
//    }
//
//    val bExit = findViewById<ImageButton>(R.id.exitBtn)
//    bExit.setOnClickListener {
//        val intent = Intent(this, QueueEditing::class.java)
//        startActivity(intent);
//    }
//
//    val recyclerView: RecyclerView = findViewById(R.id.turnsRecView)
//    recyclerView.layoutManager = LinearLayoutManager(this)
//    val turnAdapter = TurnAdapter(this)
//    val turnList = mutableListOf<Turn>()
//    mMineUserEntity?.forEach{
//        var turn = Turn(it.turnName, it.turnDesc)
//        turnList.add(0,turn)
//    }
//    recyclerView.adapter = turnAdapter
//    turnAdapter.setItems(turnList)

//}


//fun CreateTurn(view : View){
//    val intent = Intent(this, Activity_Mainqueue::class.java)
//    startActivity(intent)
//}


}