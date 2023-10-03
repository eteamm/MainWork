package com.example.mainlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainlist.adapter.MemberAdapter
import com.example.mainlist.data.Member
import com.google.gson.Gson

class ListOfParticipants : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_participants)

        val Admins = """
        [ {id: 1, Name: "example", idGroup: 2391, status: 1},  {id: 2, Name: "example", idGroup: 2391, status: 1}]
        """.trimIndent()
        val Users = """[ {id: 1, Name: "example", idGroup: 2391, status: 1},  {id: 2, Name: "example", idGroup: 2391, status: 1}]
         """.trimIndent()

        var gson = Gson()
        var ADMINS = gson?.fromJson(Admins,Array<Member>::class.java)?.toList()
        var USERS = gson?.fromJson(Users, Array<Member>::class.java)?.toList()


        val recyclerAdmins: RecyclerView = findViewById(R.id.AdminsRec)
        val recyclerUsers: RecyclerView = findViewById(R.id.UsersRec)
        recyclerAdmins.layoutManager = LinearLayoutManager(this)
        recyclerUsers.layoutManager = LinearLayoutManager(this)
        val AdminsAdapter = MemberAdapter(this)
        val UsersAdapter = MemberAdapter(this)

        recyclerAdmins.adapter = AdminsAdapter
        recyclerUsers.adapter = UsersAdapter
        val turnList = mutableListOf<Member>()
        ADMINS?.forEach {
            var memberlist = Member(it.id, it.Name, it.idGroup, it.Status)
            turnList.add(0, memberlist)
        }
        AdminsAdapter.setItems(turnList)

        val turnList2 = mutableListOf<Member>()
        USERS?.forEach {
            var memberlist = Member(it.id, it.Name, it.idGroup, it.Status)
            turnList2.add(0, memberlist)
        }
        UsersAdapter.setItems(turnList2)

        val SearchButton1 = findViewById<ImageButton>(R.id.searchButton1)
        val SearchButton2 = findViewById<ImageButton>(R.id.searchButton2)
        val AdminsTitle = findViewById<TextView>(R.id.adminss)
        val StudentsTitle = findViewById<TextView>(R.id.members)
        val SearchAdmins = findViewById<EditText>(R.id.searchAdmins)
        val SearchStudents = findViewById<EditText>(R.id.searchStudents)
        val CancelAdmins = findViewById<ImageButton>(R.id.cancelButton1)
        val CancelStudents = findViewById<ImageButton>(R.id.cancelButton2)

        SearchButton1.setOnClickListener {
            AdminsTitle.visibility = TextView.GONE
            SearchAdmins.visibility = EditText.VISIBLE
            SearchButton1.visibility = ImageButton.GONE
            CancelAdmins.visibility = ImageButton.VISIBLE
        }

        SearchButton2.setOnClickListener {
            StudentsTitle.visibility = TextView.GONE
            SearchStudents.visibility = EditText.VISIBLE
            SearchButton2.visibility = ImageButton.GONE
            CancelStudents.visibility = ImageButton.VISIBLE
        }

        CancelAdmins.setOnClickListener {
            SearchAdmins.visibility = EditText.GONE
            CancelAdmins.visibility = ImageButton.GONE
            AdminsTitle.visibility = TextView.VISIBLE
            SearchButton1.visibility = ImageButton.VISIBLE
        }

        CancelAdmins.setOnClickListener {
            SearchStudents.visibility = EditText.GONE
            CancelStudents.visibility = ImageButton.GONE
            StudentsTitle.visibility = TextView.VISIBLE
            SearchButton2.visibility = ImageButton.VISIBLE
        }
    }
}