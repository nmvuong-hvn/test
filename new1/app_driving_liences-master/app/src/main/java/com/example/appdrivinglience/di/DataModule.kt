package com.example.appdrivinglience.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.appdrivinglience.database.AppDatabase
import com.example.appdrivinglience.database.dao.CategoryLicenseDao
import com.example.appdrivinglience.database.dao.CategoryQuestionDao
import com.example.appdrivinglience.database.dao.NotificationDao
import com.example.appdrivinglience.database.dao.QuestionDao
import com.example.appdrivinglience.database.dao.TrickDao
import com.example.appdrivinglience.repository.AlarmRepository
import com.example.appdrivinglience.repository.AlarmRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "gplx.db"
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideSharePreferences(@ApplicationContext context: Context): SharedPreferences{
        return context.getSharedPreferences("APP_DRIVING_LICENSE", Context.MODE_PRIVATE);
    }
    @Provides
    @Singleton
    fun provideQuestionDao(appDatabase: AppDatabase): QuestionDao {
        return appDatabase.questionDao()
    }

    @Provides
    @Singleton
    fun provideTrickDao(appDatabase: AppDatabase): TrickDao {
        return appDatabase.trickDao()
    }

    @Provides
    @Singleton
    fun provideAlarmRepository(@ApplicationContext context: Context): AlarmRepository {
        return AlarmRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideNotificationDao(appDatabase: AppDatabase): NotificationDao {
        return appDatabase.notificationDao()
    }

    @Provides
    @Singleton
    fun provideCategoryLicenseDao(appDatabase: AppDatabase): CategoryLicenseDao {
        return appDatabase.categoryLicenseDao()
    }

    @Provides
    @Singleton
    fun provideCategoryQuestionDao(appDatabase: AppDatabase): CategoryQuestionDao {
        return appDatabase.categoryQuestionDao()
    }
}