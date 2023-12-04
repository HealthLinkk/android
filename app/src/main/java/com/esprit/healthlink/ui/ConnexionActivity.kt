package com.esprit.healthlink.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import com.esprit.healthlink.R
import com.esprit.healthlink.databinding.ActivityConnexionBinding
import com.esprit.healthlink.ui.fragments.SignInFragment
import com.esprit.healthlink.ui.fragments.SignUpFragment

class ConnexionActivity : AppCompatActivity() {
private lateinit var binding: ActivityConnexionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnexionBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the back button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        showSignInFragment()

       
         binding.txtSinscrire.setOnClickListener {
            showSignUpFragment()
        }

        binding.txtSeConnecter.setOnClickListener {
            showSignInFragment()
        }
        val successMessage = intent.getStringExtra( "successMessage")
        if (successMessage != null) {
            showSuccessAlert(successMessage)
        }
    }

    private fun showSignInFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val signInFragment = SignInFragment()


        transaction.replace(R.id.fragmentContainer, signInFragment)
        transaction.commit()
    }

    private fun showSignUpFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val signUpFragment = SignUpFragment()


        transaction.replace(R.id.fragmentContainer, signUpFragment)
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
    private fun showSuccessAlert(message: String) {
        // Show an alert or toast with the success message
        AlertDialog.Builder(this)
            .setTitle("Success")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    }
