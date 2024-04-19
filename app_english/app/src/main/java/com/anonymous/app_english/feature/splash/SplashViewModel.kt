package com.anonymous.app_english.feature.splash

import androidx.lifecycle.ViewModel
import com.anonymous.app_english.core.AccountSharePreference
import com.anonymous.app_english.feature.account_repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountSharePreference: AccountSharePreference
) : ViewModel(){

    fun saveAppFirstInstalled(){
        accountSharePreference.saveAppFirstInstalled(true)
    }

    fun getAppFirstInstalled(): Boolean{
        return accountSharePreference.getAppFirstInstalled()
    }

}