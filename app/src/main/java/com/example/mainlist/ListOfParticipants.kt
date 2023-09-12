package com.example.mainlist

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class ListOfParticipants : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_participants)

        val SearchButton1 = findViewById<ImageButton>(R.id.searchButton1)
        val SearchButton2 = findViewById<ImageButton>(R.id.searchButton2)
        val AdminsTitle = findViewById<TextView>(R.id.adminss)
        val StudentsTitle = findViewById<TextView>(R.id.members)
        val SearchAdmins = findViewById<EditText>(R.id.searchAdmins)
        val SearchStudents = findViewById<EditText>(R.id.searchStudents)
        val CancelAdmins = findViewById<ImageButton>(R.id.cancelButton1)
        val CancelStudents = findViewById<ImageButton>(R.id.cancelButton2)

        SearchButton1.setOnClickListener {
            AdminsTitle.visibility = TextView.GONE
            SearchAdmins.visibility = EditText.VISIBLE
            SearchButton1.visibility = ImageButton.GONE
            CancelAdmins.visibility = ImageButton.VISIBLE
        }

        SearchButton2.setOnClickListener {
            StudentsTitle.visibility = TextView.GONE
            SearchStudents.visibility = EditText.VISIBLE
            SearchButton2.visibility = ImageButton.GONE
            CancelStudents.visibility = ImageButton.VISIBLE
        }

        CancelAdmins.setOnClickListener {
            SearchAdmins.visibility = EditText.GONE
            CancelAdmins.visibility = ImageButton.GONE
            AdminsTitle.visibility = TextView.VISIBLE
            SearchButton1.visibility = ImageButton.VISIBLE
        }

        CancelAdmins.setOnClickListener {
            SearchStudents.visibility = EditText.GONE
            CancelStudents.visibility = ImageButton.GONE
            StudentsTitle.visibility = TextView.VISIBLE
            SearchButton2.visibility = ImageButton.VISIBLE
        }
    }
}