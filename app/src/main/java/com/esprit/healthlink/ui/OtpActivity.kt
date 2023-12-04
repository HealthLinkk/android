package com.esprit.healthlink.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.esprit.healthlink.databinding.ActivityOtpBinding
import com.esprit.healthlink.viewModel.PatientSignUpViewModel
import com.esprit.healthlink.viewModel.PatientSignUpViewModelFactory


class OtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    private lateinit var viewModel: PatientSignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, PatientSignUpViewModelFactory())[PatientSignUpViewModel::class.java]

        setContentView(binding.root)

        setupUi()
    }

    private fun setupUi() {
        binding.btnVrifier.setOnClickListener {
            // Concatenate the OTP fields
            val otp = binding.otp1.text.toString() +
                    binding.otp2.text.toString() +
                    binding.otp3.text.toString() +
                    binding.otp4.text.toString() +
                    binding.otp5.text.toString() +
                    binding.otp6.text.toString()


            // Get the data passed from SignUpFragment
            val name = intent.getStringExtra("name")
            val lastName = intent.getStringExtra("lastName")
            val password = intent.getStringExtra("password")
            val numTel = intent.getStringExtra("numTel")
            val dateNaiss = intent.getStringExtra("dateNaiss")

            // Call the ViewModel function to initiate Patient SignUp API call
            if (name != null && lastName != null && password != null && numTel != null && dateNaiss != null) {
                viewModel.PatientSignUpApiCall(name, lastName, password, numTel, dateNaiss, otp)
                if (viewModel.isSignUpSuccessful) {
                    navigateToConnexionActivity(successMessage = "Vous étes inscrit avec succées !")
                }

            }
        }
    }
    private fun navigateToConnexionActivity(successMessage: String) {
        val intent = Intent(this,ConnexionActivity::class.java).apply {
            putExtra("successMessage", successMessage)
        }
        startActivity(intent)
    }

}