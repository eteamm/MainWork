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
import com.example.mainlist.adapter.TurnAdapter
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
          name: "Васильев Андрей Антонович", 
          groupNumber: 2391,
          idUser: 5
        },
        {
          id: 4, 
          name: "Ненарокова Маргарита Олеговна", 
          groupNumber: 3242,
          idUser: 2
        },
        {
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
          id: 1, 
          name: "Глоба Валерия Владимировна", 
          groupNumber: 2391,
          idUser: 1
        }]
        """.trimIndent()



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


        val category = intent.categories
        val name : String?
        val desc : String?
        val author : String?
        var loggedUserId = 1
        var creatorUserId = 1
        if (category.contains("CreateTurn")){
            name = intent.getStringExtra("NameTurn")
            desc = intent.getStringExtra("About")
            val authorID = intent.getIntExtra("Author",0)
            author = "Васильев Андрей Антонович"
            myTurnName.text = name
            myTurnAuthor.text = author
            if (desc != null){
                if (!desc.isEmpty()){
                    val description = resources.getString(R.string.descriptionBoxTurnCurrent,desc)
                    myTurnDescription.text = description
                }
            }
            loggedUserId = authorID
            creatorUserId = authorID
            myTurnNumberOfPeople.text = "1"
            myTurnPeopleTextView.text = People[1]
        }
        else if (category.contains("EditTurn")) {
            name = intent.getStringExtra("Top")
            desc = intent.getStringExtra("About")
            author = intent.getStringExtra("Author")
            myTurnAuthor.text = author
            myTurnName.text = name
            if (desc != null){
                if (!desc.isEmpty()){
                    val description = resources.getString(R.string.descriptionBoxTurnCurrent,desc)
                    myTurnDescription.text = description
                }
            }
        }
        else if(category.contains("CurrentTurn")){
            name = intent.getStringExtra("Name")
            desc = intent.getStringExtra("Description")
            author = intent.getStringExtra("Author")
            myTurnName.text = name
            myTurnAuthor.text = author
            if (desc != null){
                if (!desc.isEmpty()){
                    val description = resources.getString(R.string.descriptionBoxTurnCurrent,desc)
                    myTurnDescription.text = description
                }
            }
            myTurnNumberOfPeople.text = intent.getIntExtra("NumberOfPeople",0).toString()
            myTurnPeopleTextView.text = People[intent.getIntExtra("NumberOfPeople",0)%10]
            loggedUserId = intent.getIntExtra("CurrentUser",0)
            creatorUserId = intent.getIntExtra("IdCreator",0)

        }
        var count = 0
        var admin = 0 // модератор!!!
        if (loggedUserId==creatorUserId){
            admin = 2
        }
        var gsonMainqueue = Gson()
        var responseMainqueue =
            gsonMainqueue?.fromJson(myJson, Array<Positions>::class.java)?.toList()


        inAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_in)
        outAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_out)

        val timer = Timer()


        var countStudent : TextView = findViewById(R.id.CountPlacestxt)




        val ButtonToPeople: Button = findViewById(R.id.turnPeopleBtn)
        val recyclerView: RecyclerView = findViewById(R.id.PositionsRec)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = false

        val WarningTxt: CardView = findViewById(R.id.WarningJoinTxt)
        val ShareBtn1: Button = findViewById(R.id.ShareBtn)

        val positionsAdapter = PositionsAdapter(this, admin)
        val positionsList = mutableListOf<Positions>()
        var b = true
        responseMainqueue?.forEach {
            if (loggedUserId != it.idUser && b) {
                count++
            } else {
                b = false
            }
            var position = Positions(it.id, it.name, it.groupNumber, it.idUser)
            positionsList.add(position)
        }
        recyclerView.adapter = positionsAdapter
        positionsAdapter.setItems(positionsList, loggedUserId)
        numberToGoTextView.text = "До твоей ближайшей очереди " + count.toString() + " позиции"
//        while (logged_user_id!= positionsList[count].idUser){
//            count = count + 1
//        }
//        NumberToGoTextView.text = positionsList[2].name.toString()



        if (loggedUserId == admin) {
            ShareBtn1.visibility = View.GONE

        }
        if (loggedUserId == creatorUserId) {
            Pencil.visibility = View.VISIBLE
        }
        else{
            Pencil.visibility = View.GONE
        }


        val JoinBtn: Button = findViewById(R.id.createTurnBtn)

        JoinBtn.setOnClickListener() {

            JoinBtn.isClickable = false
            val positionNew = Positions(
                9,
                "Yuri",
                "2391",
                loggedUserId
            ) // idUser для каждого пользователя свой
            var temp = positionsAdapter.addPosition(positionNew)

            if (temp != 0) {
                temp++
                val str = String.format(getString(R.string.warningTxtTurn), temp)
                val textWarn = findViewById<TextView>(R.id.textWarnTurn)
                textWarn.text=str
                WarningTxt.visibility = View.VISIBLE

                Handler().postDelayed({
                    WarningTxt.startAnimation(outAnimation)
                    Handler().postDelayed({
                        WarningTxt.visibility = View.GONE
                        JoinBtn.isClickable = true
                    }, 2000)
                }, 3000)

//                timer.schedule(timerTask {  }, 10000)
                //var warningtext = (WarningTxt.visibility = View.VISIBLE)
                //WarningTxt.visibility = View.VISIBLE
            }

        }
        ButtonToPeople.setOnClickListener() {
            val intent = Intent(this, MembersActivity::class.java)
            startActivity(intent)
            finish()
        }

        Pencil.setOnClickListener {
            val intent2 = Intent(this, EditTurnActivity::class.java)
            intent2.putExtra("Top", myTurnName.getText().toString())
            intent2.putExtra("About", myTurnDescription.getText().toString())
            intent2.putExtra("Author", myTurnAuthor.getText().toString())
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
