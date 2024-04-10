package com.example.appdrivinglience

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.appdrivinglience.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    companion object {
        const val CHANNEL_ID = "APP_DRIVING_LICENSE"
    }

    override fun onCreate() {
        super.onCreate()
        createNotification()


    }

    private fun createNotification(){
        // Create the NotificationChannel.
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(CHANNEL_ID, "APP_DRIVING_LICENSE", importance)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }
}