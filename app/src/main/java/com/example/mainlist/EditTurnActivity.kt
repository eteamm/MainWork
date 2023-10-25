package com.example.mainlist

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.adapter.AllowGroupAdapter
import com.example.mainlist.data.AllowGroup


class EditTurnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turn_edit)

        val noName = findViewById<TextView>(R.id.noName)
        val noGroups = findViewById<TextView>(R.id.noGroups)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val nameMassage = findViewById<EditText>(R.id.queueNameBlock)
        val goToMembers = findViewById<ImageView>(R.id.goToMembersBtn)
        val aboutQueue = findViewById<EditText>(R.id.queueDescriptionBlock)

        val intent1 = intent
        val top = intent1.getStringExtra("Top") //появление названия очереди
        nameMassage.setText(top)

        val sendAboutQueue = aboutQueue.text.toString()

        noName.visibility = TextView.GONE
        noGroups.visibility = TextView.GONE

        saveButton.setOnClickListener {
            val msg: String = nameMassage.text.toString()
            if (msg.trim().isEmpty()) {
                noName.visibility = EditText.VISIBLE
            } else {
                val intent1 = Intent(this, TurnActivity::class.java)
                intent1.putExtra("About", sendAboutQueue)
                intent1.putExtra("Top", nameMassage.getText().toString())
                startActivity(intent1)
                finish()


            }
        }

        cancelButton.setOnClickListener {
            val intent2 = Intent(this, TurnActivity::class.java)
            startActivity(intent2)
            finish()
        }

        goToMembers.setOnClickListener {
            val intent3 = Intent(this, MembersActivity::class.java)
            startActivity(intent3)
        }

        val allowEdit = findViewById<EditText>(R.id.queueGroupsBlock)
        val allowGroupsRec = findViewById<RecyclerView>(R.id.listOfGroups)
        allowGroupsRec.layoutManager = LinearLayoutManager(this)
        val allowGroupAdapter = AllowGroupAdapter(this)
        allowGroupsRec.adapter = allowGroupAdapter
        allowGroupsRec.isNestedScrollingEnabled = false;

        allowEdit.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    val s = allowEdit.text.toString()
                    val g = AllowGroup(0, s.toInt())
                    val created = allowGroupAdapter.addAllowGroup(g)

                    allowEdit.setText("");

//                    TestEditText.clearFocus()
//                    TestEditText.isCursorVisible = false

                    return true
                }
                return false
            }
        })
    }


}