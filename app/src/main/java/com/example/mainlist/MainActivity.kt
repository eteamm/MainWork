package com.example.mainlist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.adapter.MyFragment
import com.example.mainlist.adapter.TurnAdapter
import com.example.mainlist.data.Turn
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myJson = """
        [ 
            {
                id: 1, 
                name: "Зачетная неделя",
                description: "Берите с собой ручки!",
                nameCreator: "Железняк Александр Владимирович",
                idUser: 1,
                numberOfPeople: 46
            }, 
            {
                id: 2, 
                name: "Деканат Отчисления",
                description: "Стучитесь и будьте культурными!", 
                nameCreator: "Холод Иван Иванович", 
                idUser: 4,
                numberOfPeople: 36
            },
            {
            id: 1, 
            name: "Физика Экзамен",
            description: "Зачетки не забудьте.",
            nameCreator: "Леднев Михаил Георгиевич",
            idUser: 2,
            numberOfPeople: 49
            },
            {
            id: 2, 
            name: "Здравпункт",
            description: "Приносите форму М-54, справку о прививках и остальные документы.",
            nameCreator: "Сергеева Анна Анатольевна",
            idUser: 3,
            numberOfPeople: 32
            },
            {
            id: 3, 
            name: "Помощь по ТОЭ",
            description: "Подходите в коворкинг, помогаю с задачками по ТОЭ",
            nameCreator: "Кадун Никита Андреевич",
            idUser: 5,
            numberOfPeople: 77
            },
            {
            id: 4, 
            name: "Экзамен ТОЭ",
            description: "Те, кто не выполнил ИДЗ, на экзамен не допускаются",
            nameCreator: "Самоваров Иван Кириллович",
            idUser: 1,
            numberOfPeople: 61
            },
            {
            id: 5, 
            name: "Зачет по физре",
            description: "Жду всех с зачетками",
            nameCreator: "Комилов Виктор Матвеевич",
            idUser: 1,
            numberOfPeople: 101
            },
            {
            id: 6, 
            name: "Пропуски",
            description: "Если уже сходили в здравпункт и подтвердили справку, приходите в деканат",
            nameCreator: "Горин Николай Олегович",
            idUser: 5,
            numberOfPeople: 55
            }
        ]
        """.trimIndent()
        val dostupJson = """
       [
           {
            id: 1, 
            name: "Физика Экзамен",
            description: "Зачетки не забудьте.",
            nameCreator: "Леднев Михаил Георгиевич",
            idUser: 2,
            numberOfPeople: 49
            },
            {
            id: 2, 
            name: "Здравпункт",
            description: "Приносите форму М-54, справку о прививках и остальные документы.",
            nameCreator: "Сергеева Анна Анатольевна",
            idUser: 3,
            numberOfPeople: 32
            },
            {
            id: 3, 
            name: "Помощь по ТОЭ",
            description: "Подходите в коворкинг, помогаю с задачками по ТОЭ",
            nameCreator: "Кадун Никита Андреевич",
            idUser: 5,
            numberOfPeople: 77
            },
            {
            id: 4, 
            name: "Экзамен ТОЭ",
            description: "Те, кто не выполнил ИДЗ, на экзамен не допускаются",
            nameCreator: "Самоваров Иван Кириллович",
            idUser: 1,
            numberOfPeople: 61
            },
            {
            id: 5, 
            name: "Зачет по физре",
            description: "Жду всех с зачетками",
            nameCreator: "Комилов Виктор Матвеевич",
            idUser: 1,
            numberOfPeople: 101
            },
            {
            id: 6, 
            name: "Пропуски",
            description: "Если уже сходили в здравпункт и подтвердили справку, приходите в деканат",
            nameCreator: "Горин Николай Олегович",
            idUser: 5,
            numberOfPeople: 55
            }
        ]
        """.trimIndent()

        var gson = Gson()
        var MyTurns = gson?.fromJson(myJson,Array<Turn>::class.java)?.toList()
        var InDostupTurns = gson?.fromJson(dostupJson, Array<Turn>::class.java)?.toList()

        val bcreateturn = findViewById<Button>(R.id.CreateTurnBtn)
        val MyTurnsBtn = findViewById<Button>(R.id.bMy)
        val InDostupBtn = findViewById<Button>(R.id.MainScreenInDostupBtn)
        val recyclerView: RecyclerView = findViewById(R.id.turnsRec)


//        val customLinearLayout = CustomLinearLayout(this,VERTICAL,false);
//        recyclerView.layoutManager = customLinearLayout
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager =layoutManager;
        val turnAdapter = TurnAdapter(this)
        recyclerView.adapter = turnAdapter
        val turnList = mutableListOf<Turn>()
        MyTurns?.forEach {
            var turn = Turn(it.id, it.name, it.description, it.nameCreator, it.idUser, it.numberOfPeople)
            turnList.add(0, turn)
        }
        turnAdapter.setItems(turnList, true)
        recyclerView.setNestedScrollingEnabled(false);


        val bExit = findViewById<ImageView>(R.id.exitImageView)
        bExit.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }


        bcreateturn.setOnClickListener {
            val intent = Intent(this, CreateTurnActivity::class.java)
            startActivity(intent);
            finish()
        }
        MyTurnsBtn.setOnClickListener {
            MyTurnsBtn.setTextColor(application.resources.getColor(R.color.colorMain))
            InDostupBtn.setTextColor(application.resources.getColor(R.color.onButton))
            turnList.clear()
            MyTurns?.forEach {
                var turn = Turn(it.id, it.name, it.description, it.nameCreator, it.idUser, it.numberOfPeople)
                turnList.add(0, turn)
            }
            turnAdapter.setItems(turnList, true)

        }

        InDostupBtn.setOnClickListener {
            MyTurnsBtn.setTextColor(application.resources.getColor(R.color.onButton))
            InDostupBtn.setTextColor(application.resources.getColor(R.color.colorMain))
            turnList.clear()
            InDostupTurns?.forEach{
                var turn = Turn(it.id, it.name, it.description, it.nameCreator, it.idUser,it.numberOfPeople)
                turnList.add(0,turn)
            }
            turnAdapter.setItems(turnList, false)
        }


    }





}