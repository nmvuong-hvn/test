package com.anonymous.app_english.feature

import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.anonymous.app_english.R
import com.anonymous.app_english.components.dialog.CustomCommonDialog
import com.anonymous.app_english.databinding.ActivitySignInBinding
import com.anonymous.app_english.databinding.ItemDialogVocabularyBinding
import com.anonymous.app_english.feature.account.AccountState
import com.anonymous.app_english.feature.account.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding

    private val accountViewModel: AccountViewModel by viewModels()

    private lateinit var customCommonDialog : CustomCommonDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val emailAccount = accountViewModel.getEmail()
        val passAccount  = accountViewModel.getPass()

        val isCheck = accountViewModel.getIsCheck()
        binding.cbRemember.isChecked = isCheck

        binding.edtEmail.setText(emailAccount)
        binding.edtPassword.setText(passAccount)

        lifecycleScope.launch {
            accountViewModel.accountState.collectLatest {
                when(it){
                    is AccountState.Error -> Toast.makeText(this@SignInActivity,"${it.error}",Toast.LENGTH_SHORT).show()
                    AccountState.Loading ->  Toast.makeText(this@SignInActivity,"Loading",Toast.LENGTH_SHORT).show()
                    is AccountState.Success<*> ->  Toast.makeText(this@SignInActivity,"${it.dataList}",Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.layoutSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.tvForgot.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val pass = binding.edtPassword.text.toString().trim()
            if (accountViewModel.validateAccount(pass, pass,email)) {
                accountViewModel.loginAccount(email, pass)
            }
            show()
        }

        binding.cbRemember.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val pass = binding.edtPassword.text.toString().trim()

            if (binding.cbRemember.isChecked){
                accountViewModel.setIsCheck(true)
                accountViewModel.saveEmailAndPass(email,pass)
            }else {
                accountViewModel.setIsCheck(false)
                accountViewModel.clearDataCache()
            }
        }
    }

    fun show(){
        val dialog = Dialog(this)
        val binding1 = ItemDialogVocabularyBinding.inflate(layoutInflater)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.item_dialog_vocabulary)
        dialog.window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }
}