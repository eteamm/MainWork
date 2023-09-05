package com.example.mainlist

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.databinding.ActivityQueueEditingBinding


class queue_editing : AppCompatActivity() {
    lateinit var binding: ActivityQueueEditingBinding
    private val adapter = groupsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQueueEditingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noName = findViewById<TextView>(R.id.no_name)
        val noGroups = findViewById<TextView>(R.id.no_groups)
        val saveButton = findViewById<Button>(R.id.save_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)
        val nameMassage = findViewById<EditText>(R.id.queue_name_block)
        val listOfGroups = findViewById<RecyclerView>(R.id.listOfGroups)
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

        init()
    }

    private fun init(){
        binding.apply{
            listOfGroups.layoutManager = LinearLayoutManager(this@queue_editing)
            listOfGroups.adapter = adapter

        }
    }

}