package com.anonymous.app_english.feature.account_repository

import com.anonymous.app_english.feature.account.AccountState
import com.anonymous.app_english.model.Account
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun createAccount(account: Account) : Flow<AccountState>
    fun loginAccount(email: String, pass: String) : Flow<AccountState>
    fun checkEmailVerification(firebaseUser: FirebaseUser): Boolean
    fun sendEmail(email: String): Flow<AccountState>
    fun validationAccount(pass: String, confirmPass: String, email: String): Boolean
    fun logout()
}