package com.example.appdrivinglience.core

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FileSharePreference @Inject constructor(@ApplicationContext context: Context) {

    companion object{
        private const val CATEGORY_LICENSE = "CATEGORY_LICENSE"
    }
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    fun saveCategoryLicense(data: String) {
        sharedPreferences.edit().apply {
            putString(CATEGORY_LICENSE, data);
        }.apply()
    }

    fun getCategoryLicense(): String{
        return sharedPreferences.getString(CATEGORY_LICENSE,"") ?: ""
    }

    fun saveScoreForCategoryLicense(key: String, score: Int){
        sharedPreferences.edit().apply {
            putInt(key, score)
        }.apply()
    }

    fun getScoreForCategoryLicense(key: String): Int {
        return  sharedPreferences.getInt(key,-1)
    }
}