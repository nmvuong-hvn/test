package com.example.appdrivinglience.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.appdrivinglience.database.model.Notification
import com.example.appdrivinglience.service.AlarmService
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor( @ApplicationContext val context: Context) : AlarmRepository {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    override fun createAlarm(notification: Notification , timeStart: Long) {
        val intentService = Intent(context, AlarmService::class.java).apply {
            val bundle = Bundle()
            bundle.putParcelable("notification", notification)
            putExtras(bundle)
        }
        val pendingIntent = PendingIntent.getService(context, notification.id,intentService,PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC, timeStart,pendingIntent)
    }
    override fun cancelAlarm(notification: Notification) {
        val intentService = Intent(context, AlarmService::class.java).apply {
            val bundle = Bundle()
            bundle.putParcelable("notification", notification)
            putExtras(bundle)
        }
        val pendingIntent = PendingIntent.getService(context, notification.id,intentService, PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE)
        alarmManager.cancel(pendingIntent)
    }
}