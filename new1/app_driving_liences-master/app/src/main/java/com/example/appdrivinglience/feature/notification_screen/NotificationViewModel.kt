package com.example.appdrivinglience.feature.notification_screen

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdrivinglience.database.dao.NotificationDao
import com.example.appdrivinglience.database.model.Notification
import com.example.appdrivinglience.repository.AlarmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationDao: NotificationDao,
    private val alarmRepository: AlarmRepository
): ViewModel(){

    private val _notificationState = mutableStateOf<NotificationState>(NotificationState.Loading)
    val notificationState: State<NotificationState> = _notificationState

    fun insertNotification(notification: Notification){
        viewModelScope.launch {
            notificationDao.insertNotification(notification)
        }
    }

    fun getAllListNotification(){
        _notificationState.value = NotificationState.Loading
        viewModelScope.launch {
            runCatching {
                _notificationState.value = NotificationState.Success(notificationDao.getListNotification())

            }.onFailure {
                _notificationState.value = NotificationState.Error(it.message)
            }
        }
    }
    private fun reScheduleNotifications(notification: Notification, timeStart: Long){
        alarmRepository.createAlarm(notification,timeStart)
    }
    fun scheduleLeastNotification(notification: Notification){
        val leastNotification = notificationDao.getLeastNotification()
        if (leastNotification.isNotEmpty()) {
            val calendar = Calendar.getInstance()
            handleReScheduleNotifications(leastNotification[0],calendar,notification.time)
        }
    }
    fun cancelNotifications(notification: Notification){
        alarmRepository.cancelAlarm(notification)
        notificationDao.updateStatusNotification(false,notification.id)
    }

    @SuppressLint("SimpleDateFormat")
    fun rescheduleAlarms(){
       viewModelScope.launch {
           val calendar = Calendar.getInstance()
           val currentTime = calendar.timeInMillis
           val listNotifications = notificationDao.getListNotification()
           listNotifications.forEach {
               handleReScheduleNotifications(it, calendar, currentTime)
           }
       }
    }

    private fun handleReScheduleNotifications(
        it: Notification,
        calendar: Calendar,
        currentTime: Long
    ) {
        val dayOfWeek = it.daysOfWeek % 7
        val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        calendar.set(Calendar.HOUR, it.hour)
        calendar.set(Calendar.MINUTE, it.minutes)

        if (it.time <= currentTime) {
            if (dayOfWeek > currentDayOfWeek) {
                calendar.add(Calendar.DAY_OF_MONTH, dayOfWeek - currentDayOfWeek)
                notificationDao.updateNotification(calendar.timeInMillis, it.id)
            } else if (dayOfWeek < currentDayOfWeek) {
                calendar.add(Calendar.DAY_OF_MONTH, dayOfWeek - currentDayOfWeek + 7)
                notificationDao.updateNotification(calendar.timeInMillis, it.id)
    //                    val date = Date(calendar.timeInMillis)
    //                    println(SimpleDateFormat("EEEE, yyyy-MM-dd HH:mm").format(date))
            } else {
                if (calendar.timeInMillis >= currentTime) {
                    reScheduleNotifications(it, calendar.timeInMillis)
                }
                calendar.add(Calendar.DAY_OF_MONTH, 7)
                notificationDao.updateNotification(calendar.timeInMillis, it.id)
            }
        }
    }

}

sealed class NotificationState {
    data object Loading : NotificationState()
    data class Success(val listNotification: List<Notification>) : NotificationState()
    data class Error(val error: String?) : NotificationState()
}