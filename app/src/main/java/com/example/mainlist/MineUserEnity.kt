package com.example.mainlist

class MineUserEntity {

    data class MineUserInfo(
        val nickname: String,
        val email:String,
        val id: Int,
        val workStatus: String,
        val group: String,
        val errorDetail: Any
        //val data:Data
    )

    //"data": {
    // "userId": 8,
    // "avatar": "/uploads/image/20180115/1516009286213053126.jpeg",
    //  "nickname": "",
    // "gender": 0,
    // "birthday": 1525968000000,
    // "age": 0,
    // "province": "",
    // "city": "",
    //"district": "",
    //"workStatus": "Student",
    //"userType": 0
    // },

}
