package com.example.mainlist
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.adapter.AllowGroupAdapter
import com.example.mainlist.data.AllowGroup
class CreateTurnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turn_create)
        val allowGroupsRec = findViewById<RecyclerView>(R.id.allowRec)
        allowGroupsRec.layoutManager = LinearLayoutManager(this)
        val allowGroupList = mutableListOf<AllowGroup>()
        val allowEdit = findViewById<EditText>(R.id.createAllowGroup)
        val allowGroupAdapter = AllowGroupAdapter(this)
        allowGroupsRec.adapter = allowGroupAdapter
        allowGroupAdapter.setItems(allowGroupList)


        val nameTurn : EditText = findViewById(R.id.editText2)
        val warningText : TextView = findViewById(R.id.textView5)

        val saveButton = findViewById<Button>(R.id.createBtnCreate)
        val cancelButton = findViewById<Button>(R.id.backBtnCreate)
        val warningText1 : TextView = findViewById(R.id.textView14)



        saveButton.setOnClickListener {
//            val msg: String = nameMassage.text.toString()
//            if (msg.trim().isEmpty()) {
//                noName.visibility = EditText.VISIBLE
//            } else {
                val intent1 = Intent(this, TurnActivity::class.java)
//                intent.putExtra("About", sendAboutQueue)
                startActivity(intent1)
            finish()
//            }
        }

        cancelButton.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
            finish()
        }


        nameTurn.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(nameTurn.length() != 0){
                    warningText.visibility = View.GONE
                }
                else {
                    warningText.visibility = View.VISIBLE
                }
            }
        })


        allowEdit.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if(allowEdit.length() < 4 && allowEdit.length() >= 1){
                    warningText1.visibility = View.VISIBLE
                }
                else if(allowEdit.length() == 4 || allowEdit.length() == 0){
                    warningText1.visibility = View.GONE
                }
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER &&
                    allowEdit.length() == 4
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