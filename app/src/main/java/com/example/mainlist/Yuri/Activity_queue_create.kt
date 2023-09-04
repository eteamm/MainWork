package com.example.mainlist.Yuri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mainlist.MainScreen
import com.example.mainlist.R

class Activity_queue_create : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue_create)
    }

    fun ExitfromCreateTurn(view: View){
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
    }
}