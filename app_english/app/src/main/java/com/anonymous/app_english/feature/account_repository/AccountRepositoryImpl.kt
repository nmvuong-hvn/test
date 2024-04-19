package com.anonymous.app_english.feature.account_repository

import com.anonymous.app_english.feature.account.AccountState
import com.anonymous.app_english.model.Account
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AccountRepositoryImpl : AccountRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFireStore = FirebaseFirestore.getInstance()
    private var firebaseUser: FirebaseUser ?= null

    override fun createAccount(account: Account): Flow<AccountState> = callbackFlow<AccountState> {
        val result = firebaseAuth.createUserWithEmailAndPassword(account.email, account.password).await()
        if (result.user != null){
            firebaseUser = result.user!!
            firebaseFireStore.collection("Users")
                .document(account.id).set(account).await()
            result.user?.sendEmailVerification()?.await()
            trySend(AccountState.Success("Please email to verify!"))
        }else {
            trySend(AccountState.Error("user is null"))
        }

       awaitClose()
    }

    override fun loginAccount(email: String, pass: String): Flow<AccountState> = callbackFlow {
        runCatching {
            val result = firebaseAuth.signInWithEmailAndPassword(email, pass).await()
            if (checkEmailVerification(result.user!!)){
                trySend(AccountState.Success("Login successfully"))
            }else {
                trySend(AccountState.Error("Please verify email to login"))
            }
        }.onFailure {
            trySend(AccountState.Error(it.message))
        }
        awaitClose()
    }

    override fun checkEmailVerification(firebaseUser: FirebaseUser): Boolean {
        return firebaseUser.isEmailVerified
    }

    override fun sendEmail(email: String): Flow<AccountState> = callbackFlow{
        runCatching {
            firebaseAuth.sendPasswordResetEmail(email).await()
            trySend(AccountState.Success("Please check email to reset password"))
        }.onFailure {
            trySend(AccountState.Error(it.message))
        }
        awaitClose()
    }

    override fun validationAccount(pass: String, confirmPass: String, email: String): Boolean {
        if (pass.isEmpty() || pass.length < 6 ) return false
        if (pass.trim() != confirmPass.trim()) return false
        if (email.isEmpty() || !email.contains("@gmail")) return false
        return true
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

}