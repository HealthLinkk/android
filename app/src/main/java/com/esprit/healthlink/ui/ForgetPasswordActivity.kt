package com.esprit.healthlink.ui

import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import com.esprit.healthlink.R
import androidx.appcompat.widget.Toolbar

import com.esprit.healthlink.databinding.ActivityConnexionBinding
import com.esprit.healthlink.databinding.ActivityForgetPasswordBinding

private lateinit var binding: ActivityForgetPasswordBinding

class ForgetPasswordActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the back button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }
    // Override onOptionsItemSelected to handle the back button press
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // This will simulate a back button press
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Handle the back button press
    override fun onBackPressed() {
        // Perform any additional cleanup or logic
        super.onBackPressed()
    }
}