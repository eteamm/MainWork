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

class TurnActivity : AppCompatActivity() {

    private lateinit var inAnimation: Animation
    private lateinit var outAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turn)
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
        val logged_user_id = 0
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

        val intent1 = Intent(this, EditTurnActivity::class.java)

        val dataName = intent.getStringExtra("Name")
        val dataNameEdit = intent1.getStringExtra("Top")
        val dataAuthor = intent.getStringExtra("Author")
        val dataDescription = intent.getStringExtra("Description")
        val dataNumberOfPeople = intent.getIntExtra("NumberOfPeople", 0)
        if (dataNameEdit.toString().isEmpty()) {
           myTurnName.text = dataNameEdit
        } else {
            myTurnName.text = dataName
        }
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


        if (logged_user_id == admin) {
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
            val intent = Intent(this, MembersActivity::class.java)
            startActivity(intent);
            finish()
        }

        Pencil.setOnClickListener {
            val intent2 = Intent(this, EditTurnActivity::class.java)
            intent2.putExtra("Top", myTurnName.getText().toString())
            startActivity(intent2)
            finish()
        }

        val goback: ImageView = findViewById(R.id.backTurnImageView)
        goback.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val cancelButton : Button = findViewById(R.id.exitTurnBtn)
        cancelButton.setOnClickListener(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }




    }


}
