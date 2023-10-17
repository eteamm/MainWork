package com.example.mainlist.Yuri
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
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
import kotlin.concurrent.schedule
import java.util.*
import kotlin.concurrent.timerTask

import android.content.Context
class Activity_Mainqueue : AppCompatActivity() {

    private lateinit var inAnimation : Animation
    private lateinit var outAnimation : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainqueue)
        val myJson = """
        [{
          id: 1, 
          name: "Глоба Валерия Владимировна", 
          groupNumber: 2391,
          idUser: 1
        },
        {
          id: 4, 
          name: "Ненарокова Маргарита Олеговна", 
          groupNumber: 3242,
          idUser: 2
        },
        {
          id: 2, 
          name: "Васильев Андрей Антонович", 
          groupNumber: 2391,
          idUser: 3
        }]
        """.trimIndent()

        var count = 0
        val logged_user_id = 1
        val creator_user_id = 2
        val admin = 1 // модератор!!!

        val Pencil: ImageView = findViewById(R.id.editTurnImg)


        val People: Array<String> = arrayOf("человек","человек","человека","человека","человека","человек","человек","человек","человек","человек")
        val MyTurnName:TextView = findViewById(R.id.nameTurntxt)
        val MyTurnAuthor:TextView = findViewById(R.id.nameTeachertxt)
        val MyTurnDescription: TextView = findViewById(R.id.descriptionBoxtxt)
        val MyTurnNumberofPeople:TextView = findViewById(R.id.numberPeopletxt)
        val MyTurnPeopleTextView:TextView = findViewById(R.id.peopleBoxtxt)
        val NumberToGoTextView:TextView = findViewById(R.id.hintToPositiontxt)
        val GoToEdit:ImageView = findViewById(R.id.editTurnImg)
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


        inAnimation = AnimationUtils.loadAnimation(this,R.anim.alpha_in)
        outAnimation = AnimationUtils.loadAnimation(this,R.anim.alpha_out)

        val timer = Timer()



        val ButtonToPeople:Button = findViewById(R.id.turnPeopleBtn)
        val recyclerView: RecyclerView = findViewById(R.id.PositionsRec)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val WarningTxt : CardView = findViewById(R.id.WarningJoinTxt)
        val ShareBtn1 : Button = findViewById(R.id.ShareBtn)

        val positionsAdapter = PositionsAdapter(this)
        val positionsList = mutableListOf<Positions>()
        var b = true
        responseMainqueue?.forEach{
            if (logged_user_id!=it.idUser && b){
                count++
            }
            else{
                b = false
            }
            var position = Positions(it.id, it.name,it.groupNumber, it.idUser)
            positionsList.add(position)
        }
        recyclerView.adapter = positionsAdapter
        positionsAdapter.setItems(positionsList,logged_user_id, admin)
        NumberToGoTextView.text = "До твоей ближайшей очереди " + count.toString() + " позиции"
//        while (logged_user_id!= positionsList[count].idUser){
//            count = count + 1
//        }
//        NumberToGoTextView.text = positionsList[2].name.toString()

        if(logged_user_id == 3){
        GoToEdit.setOnClickListener {
            val intent2 = Intent(this, QueueEditing::class.java)
            intent2.putExtra("Top", MyTurnName.getText().toString())
            startActivity(intent2)
        }


        if(logged_user_id == 4){
            ShareBtn1.visibility = View.GONE

        }
        if(logged_user_id == creator_user_id)
        {
            Pencil.visibility = View.VISIBLE
        }



        val JoinBtn : Button = findViewById(R.id.createTurnBtn)

        JoinBtn.setOnClickListener() {


            var positionNew = Positions(9, "Yuri", "2391", logged_user_id) // idUser для каждого пользователя свой
            var temp = positionsAdapter.addPosition(positionNew)

            if(temp == 0 ) {
                WarningTxt.visibility = View.VISIBLE

                Handler().postDelayed({
                    WarningTxt.startAnimation(outAnimation)
                    Handler().postDelayed({
                        WarningTxt.visibility = View.GONE
                    }, 2000)
                }, 3000)

//                timer.schedule(timerTask {  }, 10000)
                //var warningtext = (WarningTxt.visibility = View.VISIBLE)
                //WarningTxt.visibility = View.VISIBLE
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