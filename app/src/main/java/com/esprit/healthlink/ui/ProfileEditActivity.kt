package com.esprit.healthlink.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esprit.healthlink.databinding.ActivityEditProfileBinding
import com.esprit.healthlink.databinding.ActivityHomepageBinding

class ProfileEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}