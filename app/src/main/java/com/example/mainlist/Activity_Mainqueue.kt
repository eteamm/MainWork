package com.example.mainlist
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.adapter.PositionsAdapter
import com.example.mainlist.data.Positions
import com.google.gson.Gson
import java.util.*

class Activity_Mainqueue : AppCompatActivity() {

    private lateinit var inAnimation: Animation
    private lateinit var outAnimation: Animation

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
        val logged_user_id = 3
        val creator_user_id = 2
        val admin = 0 // модератор!!!

        val Pencil: ImageView = findViewById(R.id.editTurnImg)


        val People: Array<String> = arrayOf(
            "человек",
            "человек",
            "человека",
            "человека",
            "человека",
            "человек",
            "человек",
            "человек",
            "человек",
            "человек"
        )
        val myTurnName: TextView = findViewById(R.id.nameTurntxt)
        val myTurnAuthor: TextView = findViewById(R.id.nameTeachertxt)
        val myTurnDescription: TextView = findViewById(R.id.descriptionBoxtxt)
        val myTurnNumberOfPeople: TextView = findViewById(R.id.numberPeopletxt)
        val myTurnPeopleTextView: TextView = findViewById(R.id.peopleBoxtxt)
        val numberToGoTextView: TextView = findViewById(R.id.hintToPositiontxt)

//        val createPosition = findViewById<Button>(R.id.createTurnBtn)
//        createPosition.setOnClickListener{
//
//        }

        val dataName = intent.getStringExtra("Name")
        val dataAuthor = intent.getStringExtra("Author")
        val dataDescription = intent.getStringExtra("Description")
        val dataNumberOfPeople = intent.getIntExtra("NumberOfPeople", 0)
        myTurnName.text = dataName
        myTurnAuthor.text = dataAuthor
        myTurnDescription.text = "Подробнее: " + dataDescription
        myTurnNumberOfPeople.text = dataNumberOfPeople.toString()
        myTurnPeopleTextView.text = People[dataNumberOfPeople % 10]
        var gsonMainqueue = Gson()
        var responseMainqueue =
            gsonMainqueue?.fromJson(myJson, Array<Positions>::class.java)?.toList()


        inAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_in)
        outAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_out)

        val timer = Timer()


        val ButtonToPeople: Button = findViewById(R.id.turnPeopleBtn)
        val recyclerView: RecyclerView = findViewById(R.id.PositionsRec)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val WarningTxt: CardView = findViewById(R.id.WarningJoinTxt)
        val ShareBtn1: Button = findViewById(R.id.ShareBtn)

        val positionsAdapter = PositionsAdapter(this)
        val positionsList = mutableListOf<Positions>()
        var b = true
        responseMainqueue?.forEach {
            if (logged_user_id != it.idUser && b) {
                count++
            } else {
                b = false
            }
            var position = Positions(it.id, it.name, it.groupNumber, it.idUser)
            positionsList.add(position)
        }
        recyclerView.adapter = positionsAdapter
        positionsAdapter.setItems(positionsList, logged_user_id, admin)
        numberToGoTextView.text = "До твоей ближайшей очереди " + count.toString() + " позиции"
//        while (logged_user_id!= positionsList[count].idUser){
//            count = count + 1
//        }
//        NumberToGoTextView.text = positionsList[2].name.toString()

        Pencil.setOnClickListener {
            val intent2 = Intent(this, QueueEditing::class.java)
            intent2.putExtra("Top", myTurnName.getText().toString())
            startActivity(intent2)
        }


        if (admin == 0) {
            ShareBtn1.visibility = View.GONE

        }
        if (logged_user_id == creator_user_id) {
            Pencil.visibility = View.VISIBLE
        }


        val JoinBtn: Button = findViewById(R.id.createTurnBtn)

        JoinBtn.setOnClickListener() {


            var positionNew = Positions(
                9,
                "Yuri",
                "2391",
                logged_user_id
            ) // idUser для каждого пользователя свой
            var temp = positionsAdapter.addPosition(positionNew)

            if (temp == 0) {
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
        val goback: ImageView = findViewById(R.id.backTurnImageView)
        goback.setOnClickListener() {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
            finish()
        }




    }


}
