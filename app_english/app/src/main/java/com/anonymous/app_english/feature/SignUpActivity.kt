package com.anonymous.app_english.feature

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.anonymous.app_english.R
import com.anonymous.app_english.databinding.ActivitySignUpBinding
import com.anonymous.app_english.feature.account.AccountState
import com.anonymous.app_english.feature.account.AccountViewModel
import com.anonymous.app_english.model.Account
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint

class SignUpActivity : AppCompatActivity() {

    private val accountViewModel: AccountViewModel by viewModels()
    private lateinit var binding:ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            accountViewModel.accountState.collectLatest {
                when(it){
                    is AccountState.Error -> Toast.makeText(this@SignUpActivity,"${it.error}",Toast.LENGTH_SHORT).show()
                    AccountState.Loading -> println("Loading...")
                    is AccountState.Success<*> -> {
                        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            val passwordConfirm = binding.edtConfirmPassword.text.toString().trim()
            if (accountViewModel.validateAccount(password, passwordConfirm,email)){
                val account = Account(
                    id = "${System.currentTimeMillis()}",
                    email = email,
                    password = password
                )
                accountViewModel.registerAccount(account = account)
            }else{
                Toast.makeText(this,"Loi dang ky",Toast.LENGTH_SHORT).show()
            }
        }
    }
}