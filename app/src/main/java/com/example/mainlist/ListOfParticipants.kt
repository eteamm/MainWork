package com.example.mainlist

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class ListOfParticipants : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_participants)

        val pic01 = findViewById<EditText>(R.id.searchBlock)
        val pic02 = findViewById<EditText>(R.id.searchBlock2)
        pic01.visibility = EditText.INVISIBLE
        pic02.visibility = EditText.INVISIBLE

        val buttonSearh1 = findViewById<Button>(R.id.SearchButton1)
        buttonSearh1.setOnClickListener {
            val pic1 = findViewById<TextView>(R.id.HeadingAdmin)
            val pic2 = findViewById<ImageView>(R.id.line1)
            pic1.visibility = TextView.INVISIBLE
            pic2.visibility = ImageView.INVISIBLE
            pic01.visibility = EditText.VISIBLE
        }

        val buttonSearh2 = findViewById<Button>(R.id.SearchButton2)
        buttonSearh2.setOnClickListener {
            val pic1 = findViewById<TextView>(R.id.HeadingStudents)
            val pic2 = findViewById<ImageView>(R.id.line2)
            pic1.visibility = TextView.INVISIBLE
            pic2.visibility = ImageView.INVISIBLE
            pic02.visibility = EditText.VISIBLE
        }
    }
}