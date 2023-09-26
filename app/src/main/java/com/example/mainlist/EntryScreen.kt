package com.example.mainlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EntryScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_screen)
        val ToMainScreenBtn = findViewById<Button>(R.id.ToMainScreenBtn)
        ToMainScreenBtn.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent);
        }
    }
}