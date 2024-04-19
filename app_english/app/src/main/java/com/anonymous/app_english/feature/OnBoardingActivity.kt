package com.anonymous.app_english.feature

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anonymous.app_english.R
import com.anonymous.app_english.databinding.ActivityOnBoardingBinding
import com.anonymous.app_english.feature.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnNext.setOnClickListener {
            splashViewModel.saveAppFirstInstalled()
            val intent = Intent(this, LanguageDisplayActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}