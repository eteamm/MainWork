package com.example.mainlist

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class queue_editing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue_editing)

        val noName = findViewById<TextView>(R.id.no_name)
        val noGroups = findViewById<TextView>(R.id.no_groups)
        val saveButton = findViewById<Button>(R.id.save_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)
        val nameMassage = findViewById<EditText>(R.id.queue_name_block)
        val listOfGroups = findViewById<ListView>(R.id.listOfGroups)
        val nameGroups = findViewById<EditText>(R.id.queue_groups_block)
        noName.visibility = TextView.GONE
        noGroups.visibility = TextView.GONE
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

        var groups = ArrayList<String>()

        val adapter = ArrayAdapter(this, R.layout.list_item, R.id.group, groups)
        listOfGroups.adapter = adapter

        nameGroups.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) if (keyCode == KeyEvent.KEYCODE_ENTER) {
                groups.add(0, nameGroups.getText().toString())
                adapter.notifyDataSetChanged()
                nameGroups.setText("")
                return@OnKeyListener true
            }
            false
        })

    }
}