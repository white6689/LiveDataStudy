package com.example.livedatas.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ChatTable")
data class Chat(
    var name:String,
    var content:String
){
    @PrimaryKey(autoGenerate = true) var id:Int=0
}
