package com.anonymous.app_english.di

import android.content.Context
import com.anonymous.app_english.core.AccountSharePreference
import com.anonymous.app_english.feature.account_repository.AccountRepository
import com.anonymous.app_english.feature.account_repository.AccountRepositoryImpl
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
    fun providesAccountRepository(): AccountRepository{
        return AccountRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providesAccountSharePreferences(
        @ApplicationContext context: Context
    ): AccountSharePreference{
        return AccountSharePreference(context = context)
    }
}