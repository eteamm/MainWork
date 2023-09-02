package com.example.mainlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class queue_editing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue_editing)

        val noName = findViewById<TextView>(R.id.no_name)
        val saveButton = findViewById<Button>(R.id.save_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)
        val nameMassage = findViewById<EditText>(R.id.queue_name_block)
        noName.visibility = EditText.GONE
        saveButton.setOnClickListener {
            val msg: String = nameMassage.text.toString()
            if (msg.trim().length == 0) {
                noName.visibility = EditText.VISIBLE
            } else {
                val intent = Intent(this, ListOfParticipants::class.java)
                startActivity(intent)
            }
        }
        cancelButton.setOnClickListener {
            val intent = Intent(this, ListOfParticipants::class.java)
            startActivity(intent)
        }



    }
}