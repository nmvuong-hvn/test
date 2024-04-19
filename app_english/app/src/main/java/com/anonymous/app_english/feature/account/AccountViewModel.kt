package com.anonymous.app_english.feature.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.app_english.core.AccountSharePreference
import com.anonymous.app_english.feature.account_repository.AccountRepository
import com.anonymous.app_english.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AccountViewModel @Inject constructor(
   private val accountRepository: AccountRepository,
   private val accountSharePreference: AccountSharePreference
): ViewModel(){
    private val _accountState = MutableStateFlow<AccountState>(AccountState.Loading)
    val accountState : StateFlow<AccountState> = _accountState

    fun registerAccount(account: Account){
        viewModelScope.launch {
            accountRepository.createAccount(account).collectLatest {
                when(it){
                    is AccountState.Error -> _accountState.value = it
                    AccountState.Loading -> _accountState.value = it
                    is AccountState.Success<*> -> _accountState.value = it
                }
            }
        }
    }

    fun loginAccount(email: String , pass : String ) {
        viewModelScope.launch {
            accountRepository.loginAccount(email,pass).collectLatest {
                when(it){
                    is AccountState.Error -> _accountState.value = it
                    AccountState.Loading -> _accountState.value = it
                    is AccountState.Success<*> -> _accountState.value = it
                }
            }
        }
    }
    fun validateAccount(pass: String , passConfirm : String, email: String ): Boolean{
        return accountRepository.validationAccount(pass,passConfirm,email)
    }

    fun saveEmailAndPass(email: String, pass: String){
        accountSharePreference.saveEmailApp(email)
        accountSharePreference.savePasswordApp(pass)
    }
    fun getEmail(): String{
        return accountSharePreference.getEmailApp()
    }
    fun getPass(): String {
        return accountSharePreference.getPasswordApp()
    }

    fun clearDataCache(){
        accountSharePreference.clearDataCache()
    }

    fun setIsCheck(data: Boolean){
        accountSharePreference.saveIsCheck(data)
    }
    fun getIsCheck(): Boolean{
        return accountSharePreference.getIsCheck()
    }
}

sealed class AccountState {
    data object Loading : AccountState()
    data class Success<out T>(val dataList : T) : AccountState()
    data class Error(val error : String?): AccountState()
}