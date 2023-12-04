package com.esprit.healthlink.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.esprit.healthlink.R

import com.esprit.healthlink.databinding.ActivitySignInDoctorBinding
import com.esprit.healthlink.ui.fragments.BoardingFragment
import com.esprit.healthlink.ui.fragments.SignInFragment

class DoctorSignInActivity  : AppCompatActivity(){
    private lateinit var binding: ActivitySignInDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInDoctorBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the back button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        showBoardingFragment()


    }
    private fun showBoardingFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val boardingFragment = BoardingFragment()


        transaction.replace(R.id.fragmentContainer, boardingFragment)
        transaction.commit()
    }
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