package com.example.livedatas.background

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.example.livedatas.database.AppDatabase
import com.example.livedatas.database.Chat

class NotificationListener:NotificationListenerService() {
    private lateinit var database:AppDatabase
    val TAG="notificationService"

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.d(TAG, "listener 연결")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        val notification=sbn.notification
        val packageName=sbn.packageName
        if(packageName!=null && packageName=="com.kakao.talk"){
            database=AppDatabase.getInstance(this)!!
            val extras=sbn.notification.extras
            val name=extras.getString(Notification.EXTRA_TITLE)
            val text=extras.getString(Notification.EXTRA_TEXT)

            if(name!=null && text!=null){
                database.chatDao().insert(Chat(name, text))
                Log.d(TAG, "onNotificationPosted:: name: $name, contents: $text")
            }
        }


    }
}