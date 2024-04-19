package com.anonymous.app_english.feature

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anonymous.app_english.R
import com.anonymous.app_english.databinding.ActivityLanguageDisplayBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint

class LanguageDisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLanguageDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnDone .setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.americanTvLanguage.setOnClickListener {
            setLocalLanguage("en")
        }
        binding.vietnamTvLanguage.setOnClickListener {
            setLocalLanguage("vi")
        }
    }

    private fun setLocalLanguage(data: String){
        val resourceConfiguration = resources.configuration
        val locale = Locale(data)
        resourceConfiguration.setLocale(locale)
        resourceConfiguration.setLayoutDirection(locale)
        createConfigurationContext(resourceConfiguration)

    }
}