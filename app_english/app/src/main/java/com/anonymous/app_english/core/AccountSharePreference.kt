package com.anonymous.app_english.core

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AccountSharePreference @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        private const val APP_FIRST_INSTALLED = "APP_FIRST_INSTALLED"
        private const val APP_NAME= "APP_NAME"

        private const val APP_EMAIL = "APP_EMAIL"
        private const val APP_PASSWORD = "APP_PASSWORD"
        private const val IS_CHECKED = "IS_CHECKED"
    }

    private val sharePreference = context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE)

    fun saveAppFirstInstalled(data: Boolean){
        sharePreference.edit().apply {
            putBoolean(APP_FIRST_INSTALLED, data)
        }.apply()
    }

    fun getAppFirstInstalled(): Boolean {
        return sharePreference.getBoolean(APP_FIRST_INSTALLED, false)
    }

    fun  savePasswordApp(data: String){
        sharePreference.edit().apply {
            putString(APP_PASSWORD,data)
        }.apply()
    }

    fun saveEmailApp(data: String){
        sharePreference.edit().apply {
            putString(APP_EMAIL,data)
        }.apply()
    }

    fun getPasswordApp(): String {
        return sharePreference.getString(APP_PASSWORD,"") ?: ""
    }

    fun getEmailApp(): String {
        return sharePreference.getString(APP_EMAIL,"") ?: ""
    }
    fun clearDataCache(){
        sharePreference.edit().apply {
            clear()
        }.apply()
    }

    fun saveIsCheck(data: Boolean){
        sharePreference.edit().apply {
            putBoolean(IS_CHECKED, data)
        }.apply()
    }
    fun getIsCheck(): Boolean{
        return sharePreference.getBoolean(IS_CHECKED,false)
    }
}