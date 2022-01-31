package com.example.livedatas.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ChatDao {
    @Insert
    fun insert(chat:Chat)
    @Update
    fun update(chat: Chat)

    @Delete
    fun delete(chat: Chat)

    @Query("SELECT * FROM ChatTable")
    fun getAll():LiveData<List<Chat>>
}