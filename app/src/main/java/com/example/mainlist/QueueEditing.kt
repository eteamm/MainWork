package com.example.mainlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.Yuri.Activity_Mainqueue
import com.example.mainlist.adapter.AllowGroupAdapter
import com.example.mainlist.data.AllowGroup
import com.example.mainlist.databinding.ActivityQueueEditingBinding


class QueueEditing : AppCompatActivity() {
    lateinit var binding: ActivityQueueEditingBinding
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
        val goToMembers = findViewById<ImageView>(R.id.goToMembersBtn)
        val aboutQueue = findViewById<EditText>(R.id.queueDescriptionBlock)

        val intent = intent
        val top = intent.getStringExtra("Top")
        nameMassage.setText(top)

        val sendAboutQueue = aboutQueue.text.toString()

        noName.visibility = TextView.GONE
        noGroups.visibility = TextView.GONE

        saveButton.setOnClickListener {
            val msg: String = nameMassage.text.toString()
            if (msg.trim().length == 0) {
                noName.visibility = EditText.VISIBLE
            } else {
                val intent = Intent(this, MainScreen::class.java)
                intent.putExtra("About", sendAboutQueue)
                startActivity(intent)
            }
        }

        cancelButton.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }

        goToMembers.setOnClickListener {
            val intent = Intent(this, ListOfParticipants::class.java)
            startActivity(intent)
        }

        val allowGroupsRec = findViewById<RecyclerView>(R.id.listOfGroups)
        allowGroupsRec.layoutManager = LinearLayoutManager(this)
        val allowGroupList = mutableListOf<AllowGroup>()
        val group = AllowGroup(0, 2391)
        val group2 = AllowGroup(1, 2392)
        val group3 = AllowGroup(2, 2393)

        allowGroupList.add(group)
        allowGroupList.add(group2)
        allowGroupList.add(group3)
        val allowgroupAdapter = AllowGroupAdapter(this)
        allowGroupsRec.adapter = allowgroupAdapter
        allowgroupAdapter.setItems(allowGroupList)
        val text = allowgroupAdapter.itemCount.toString()

        val duration = Toast.LENGTH_SHORT
    }

    fun ExitfromCreateTurn(view: View){
        val intent = Intent(this, Activity_Mainqueue::class.java)
        startActivity(intent)
    }

}