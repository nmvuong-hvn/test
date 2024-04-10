package com.example.appdrivinglience.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.appdrivinglience.MyApplication.Companion.CHANNEL_ID
import com.example.appdrivinglience.R
import com.example.appdrivinglience.database.model.Notification

class AlarmService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.extras?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable("notification", Notification::class.java)?.let { noti ->
                    showBasicNotification(noti,this)
                }
            } else {
                (it.getParcelable("notification") as? Notification)?.let { data ->
                    showBasicNotification(data,this)
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }
    private fun showBasicNotification(notification: Notification, context: Context){
        val notificationManager =  context.getSystemService(NotificationManager::class.java)
        val notificationCompat = NotificationCompat.Builder(context,CHANNEL_ID)
            .setContentTitle("Thông báo")
            .setContentText(notification.contentNotification)
            .setSmallIcon(R.drawable.notification)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
           notification.id , notificationCompat
        )
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}