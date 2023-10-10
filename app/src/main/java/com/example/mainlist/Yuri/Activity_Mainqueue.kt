package com.example.mainlist.Yuri
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.ListOfParticipants
import com.example.mainlist.MainScreen
import com.example.mainlist.QueueEditing
import com.example.mainlist.R
import com.example.mainlist.adapter.PositionsAdapter
import com.example.mainlist.data.Positions
import com.google.gson.Gson
class Activity_Mainqueue : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainqueue)
        val myJson = """
        [{
          id: 1, 
          name: "Глоба Валерия Владимировна", 
          groupNumber: 2391,
          id_user: 1
        },
        {
          id: 4, 
          name: "Ненарокова Маргарита Олеговна", 
          groupNumber: 3242,
          id_user: 3
        },
        {
          id: 2, 
          name: "Васильев Андрей Антонович", 
          groupNumber: 2391,
          id_user: 2
        }]
        """.trimIndent()


        val logged_user_id = 4
        val creator_user_id = 1
        val admin = 2 // модератор!!!
        val People: Array<String> = arrayOf("человек","человек","человека","человека","человека","человек","человек","человек","человек","человек")
        val MyTurnName:TextView = findViewById(R.id.nameTurntxt)
        val MyTurnAuthor:TextView = findViewById(R.id.nameTeachertxt)
        val MyTurnDescription: TextView = findViewById(R.id.descriptionBoxtxt)
        val MyTurnNumberofPeople:TextView = findViewById(R.id.numberPeopletxt)
        val MyTurnPeopleTextView:TextView = findViewById(R.id.peopleBoxtxt)
        val dataName = intent.getStringExtra("Name")
        val dataAuthor = intent.getStringExtra("Author")
        val dataDescription = intent.getStringExtra("Description")
        val dataNumberOfPeople = intent.getIntExtra("NumberOfPeople",0)
        MyTurnName.text = dataName
        MyTurnAuthor.text = dataAuthor
        MyTurnDescription.text = "Подробнее: " + dataDescription
        MyTurnNumberofPeople.text = dataNumberOfPeople.toString()
        MyTurnPeopleTextView.text = People[dataNumberOfPeople % 10]
        var gsonMainqueue = Gson()
        var responseMainqueue = gsonMainqueue?.fromJson(myJson, Array<Positions>::class.java)?.toList()

        val ButtonToPeople:Button = findViewById(R.id.turnPeopleBtn)
        val recyclerView: RecyclerView = findViewById(R.id.PositionsRec)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val WarningTxt : CardView = findViewById(R.id.WarningJoinTxt)
        val ShareBtn1 : Button = findViewById(R.id.ShareBtn)

        val positionsAdapter = PositionsAdapter(this)
        val positionsList = mutableListOf<Positions>()
        responseMainqueue?.forEach{
            var position= Positions(it.id, it.name,it.groupNumber, it.idUser)
            positionsList.add(0,position)
        }
        recyclerView.adapter = positionsAdapter
        positionsAdapter.setItems(positionsList)


        if(logged_user_id == 4){
            ShareBtn1.visibility = View.GONE

        }

        val JoinBtn : Button = findViewById(R.id.createTurnBtn)

        JoinBtn.setOnClickListener() {


            var positionNew = Positions(9, "Yuri", "2391", 4) // idUser для каждого пользователя свой
            var temp = positionsAdapter.addPosition(positionNew)

            if(temp == 0 ) {
                WarningTxt.visibility = View.VISIBLE
            }

        }
        ButtonToPeople.setOnClickListener() {
            val intent = Intent(this, ListOfParticipants::class.java)
            startActivity(intent);
        }
        val goback : ImageView = findViewById(R.id.backTurnImageView)
        goback.setOnClickListener(){
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
            finish()
        }
    }



fun ToEditTurn(view: View){
    val intent = Intent(this, QueueEditing::class.java)
    startActivity(intent)
}
fun ExitfromTurn(view: View){
    val intent = Intent(this, MainScreen::class.java)
    startActivity(intent)
}
}