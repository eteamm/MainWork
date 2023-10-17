package com.example.mainlist.Yuri
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.MainScreen
import com.example.mainlist.R
import com.example.mainlist.adapter.AllowGroupAdapter
import com.example.mainlist.data.AllowGroup
class Activity_queue_create : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue_create)
        val allowGroupsRec = findViewById<RecyclerView>(R.id.allowRec)
        allowGroupsRec.layoutManager = LinearLayoutManager(this)
        val allowGroupList = mutableListOf<AllowGroup>()
        val TestEditText = findViewById<EditText>(R.id.editText4)
        val TestTextView = findViewById<TextView>(R.id.textView9)
        val allowgroupAdapter = AllowGroupAdapter(this)
        allowGroupsRec.adapter = allowgroupAdapter
        allowgroupAdapter.setItems(allowGroupList)
        val text = allowgroupAdapter.itemCount.toString()

        val duration = Toast.LENGTH_SHORT

        TestEditText.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    val s = TestEditText.text.toString()
                    val g = AllowGroup(0, s.toInt())
                    val created = allowgroupAdapter.addAllowGroup(g)

                    TestEditText.setText("");
//                    TestEditText.clearFocus()
//                    TestEditText.isCursorVisible = false

                    return true
                }
                return false
            }
        })


    }


    fun ExitfromCreateTurn(view: View){
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
    }
}