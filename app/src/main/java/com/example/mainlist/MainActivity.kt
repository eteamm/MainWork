package com.example.mainlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.buttonRita)
        val UserName = findViewById<TextView>(R.id.UserNameMain)
        val Status = findViewById<TextView>(R.id.StatusMain)
        val Group = findViewById<TextView>(R.id.GroupMain)
        if (mMineUserEntity != null) {
            UserName.text =mMineUserEntity.nickname
        }
        if (mMineUserEntity != null) {
            Status.text =mMineUserEntity.workStatus
        }
        if (mMineUserEntity != null) {
            Group.text =mMineUserEntity.group
        }
        button.setOnClickListener {
            val intent = Intent(this, ListOfParticipants::class.java)
            startActivity(intent);
        }

        val button1 = findViewById<Button>(R.id.buttonRita2)
        button1.setOnClickListener {
            val intent = Intent(this, queue_editing::class.java)
            startActivity(intent);
        }

        val button2 = findViewById<Button>(R.id.buttonYra)
        button2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent);
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomRecyclerAdapter(getCatList())

    }

    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("$i element") }
        return data
    }

    private fun getCatList(): List<String> {
        return this.resources.getStringArray(R.array.cat_names).toList()
    }


    fun CreateTurn(view : View){
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }


}