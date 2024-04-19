package com.anonymous.app_english

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anonymous.app_english.feature.LanguageDisplayActivity
import com.anonymous.app_english.feature.OnBoardingActivity
import com.anonymous.app_english.feature.SignInActivity
import com.anonymous.app_english.feature.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private val splashViewModel : SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this,"${splashViewModel.getAppFirstInstalled()} ==== " , Toast.LENGTH_SHORT).show()
            if (splashViewModel.getAppFirstInstalled()){
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                splashViewModel.saveAppFirstInstalled()
                val intent = Intent(this, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }

        },3000)
    }
}