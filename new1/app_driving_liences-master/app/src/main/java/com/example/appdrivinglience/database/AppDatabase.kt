package com.example.appdrivinglience.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appdrivinglience.database.dao.CategoryLicenseDao
import com.example.appdrivinglience.database.dao.CategoryQuestionDao
import com.example.appdrivinglience.database.dao.ExaminationDao
import com.example.appdrivinglience.database.dao.ImageQuestionDao
import com.example.appdrivinglience.database.dao.NotificationDao
import com.example.appdrivinglience.database.dao.QuestionDao
import com.example.appdrivinglience.database.dao.QuestionWrongDao
import com.example.appdrivinglience.database.dao.TrickDao
import com.example.appdrivinglience.database.model.CategoryLicense
import com.example.appdrivinglience.database.model.CategoryQuestion
import com.example.appdrivinglience.database.model.Examination
import com.example.appdrivinglience.database.model.ImageQuestion
import com.example.appdrivinglience.database.model.Notification
import com.example.appdrivinglience.database.model.Question
import com.example.appdrivinglience.database.model.QuestionWrongModel
import com.example.appdrivinglience.feature.trick_screen.TrickModel

@Database(
    entities = [CategoryLicense::class, CategoryQuestion::class,
        Examination::class, ImageQuestion::class, Question::class,
        TrickModel::class, Notification::class, QuestionWrongModel::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun categoryLicenseDao(): CategoryLicenseDao
    abstract fun categoryQuestionDao(): CategoryQuestionDao
    abstract fun trickDao(): TrickDao
    abstract fun notificationDao(): NotificationDao
    abstract fun questionWrongDao(): QuestionWrongDao
}