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
{
  "nickname": "Nikita Kadun",
  "email": "kadunnikitacom@gmail.com",
  "id": "1",
  "workStatus": "Student",
  "group": "2391",
  "errorDetail": null
}
""".trimIndent()
        var gson = Gson()
        var mMineUserEntity = gson?.fromJson(myJson, MineUserEntity.MineUserInfo::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainscreen)
        val UserName = findViewById<TextView>(R.id.userNameMain)
        val Status = findViewById<TextView>(R.id.statusMain)
        val Group = findViewById<TextView>(R.id.groupMain)
        if (mMineUserEntity != null) {
            UserName.text =mMineUserEntity.nickname
        }
        if (mMineUserEntity != null) {
            Status.text =mMineUserEntity.workStatus
        }
        if (mMineUserEntity != null) {
            Group.text =mMineUserEntity.group
        }
        val bcreateturn = findViewById<Button>(R.id.bCreateTurn)
        bcreateturn.setOnClickListener {
            val intent = Intent(this, ListOfParticipants::class.java)
            startActivity(intent);
        }

        val bExit = findViewById<ImageButton>(R.id.exitBtn)
        bExit.setOnClickListener {
            val intent = Intent(this, QueueEditing::class.java)
            startActivity(intent);
        }

        val recyclerView: RecyclerView = findViewById(R.id.rec)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        val turnAdapter = TurnAdapter(this)
        val turnList = mutableListOf<Turn>()
        var turn = Turn("Name","Descre")
//        turn.turnDescOut = "Описаниееее"
        turnList.add(0,turn)
        recyclerView.adapter = TurnAdapter(turnList);
//        turnAdapter.setItems(turnList)

    }

    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("$i element") }
        return data
    }

    private fun getCatList(): List<Char> {

        val jsonList =
            """
{
  "nickname": "Nikita Kadun",
  "email": "kadunnikitacom@gmail.com",
  "id": "1",
  "workStatus": "Student",
  "group": [ "2391","2392","2370","2371"]
  "errorDetail": null
}
"""

        return jsonList.toList()
    }


    fun CreateTurn(view : View){
        val intent = Intent(this, Activity_Mainqueue::class.java)
        startActivity(intent)
    }



}