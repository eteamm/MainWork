package com.example.mainlist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.databinding.ActivityQueueEditingBinding


class QueueEditing : AppCompatActivity() {
    lateinit var binding: ActivityQueueEditingBinding
    private val adapter = groupsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQueueEditingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noName = findViewById<TextView>(R.id.noName)
        val noGroups = findViewById<TextView>(R.id.noGroups)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val nameMassage = findViewById<EditText>(R.id.queueNameBlock)
        val listOfGroups = findViewById<RecyclerView>(R.id.listOfGroups)
        val nameGroups = findViewById<EditText>(R.id.queueGroupsBlock)
        noName.visibility = TextView.GONE
        noGroups.visibility = TextView.GONE
        saveButton.setOnClickListener {
            val msg: String = nameMassage.text.toString()
            if (msg.trim().length == 0) {
                noName.visibility = EditText.VISIBLE
            } else {
                val intent = Intent(this, MainScreen::class.java)
                startActivity(intent)
            }
        }
        cancelButton.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }

        init()
    }

    private fun init(){
        binding.apply{
            listOfGroups.layoutManager = LinearLayoutManager(this@QueueEditing)
            listOfGroups.adapter = adapter

        }
    }

}