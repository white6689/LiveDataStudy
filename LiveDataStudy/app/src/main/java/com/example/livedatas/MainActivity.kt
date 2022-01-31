package com.example.livedatas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.livedatas.background.NotificationListener
import com.example.livedatas.database.AppDatabase
import com.example.livedatas.database.Chat
import com.example.livedatas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var adapter:RVAdapter
    lateinit var database:AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
        startService(Intent(this,NotificationListener::class.java ))

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        database= AppDatabase.getInstance(this)!!
        database.chatDao().getAll().observe(this, Observer {
            //데이터가 바뀌면 할일
            adapter.addItems(it)
            binding.mainChatListRecyclerView.scrollToPosition(adapter.itemCount-1)
        })
    }
    private fun initRV(){

        val linearLayoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        linearLayoutManager.stackFromEnd=true
        binding.mainChatListRecyclerView.layoutManager=linearLayoutManager

        adapter = RVAdapter()
        binding.mainChatListRecyclerView.adapter=adapter
    }
}
