package com.esprit.healthlink.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.app.DatePickerDialog
import android.content.Intent
import android.widget.Toast

import java.text.SimpleDateFormat
import java.util.*

import com.esprit.healthlink.databinding.FragmentSignUpBinding
import com.esprit.healthlink.ui.OtpActivity
import com.esprit.healthlink.viewModel.DoctorsViewModel
import com.esprit.healthlink.viewModel.DoctorsViewModelFactory
import com.esprit.healthlink.viewModel.PatientSignUpViewModel
import com.esprit.healthlink.viewModel.PatientSignUpViewModelFactory

class SignUpFragment : Fragment() {
    private lateinit var binding:FragmentSignUpBinding
    private lateinit var viewModel: PatientSignUpViewModel

    private val calendar = Calendar.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, PatientSignUpViewModelFactory())[PatientSignUpViewModel::class.java]

        setupUi()
        binding.btnDatePicker.setOnClickListener {
            // Show the DatePicker dialog
            showDatePicker()
        }


        return binding.root
    }
    private fun setupUi() {
        binding.btnSinscrire.setOnClickListener {
            // Get the values from your UI fields
            val name = binding.Nom.text.toString()
            val lastName = binding.Prenom.text.toString()
            val password = binding.Password.text.toString()
            val numTel = binding.NumTel.text.toString()
            val dateNaiss = binding.tvSelectedDate.text.toString()

            if (name.isNotEmpty() && lastName.isNotEmpty() && password.isNotEmpty() && numTel.isNotEmpty() && dateNaiss.isNotEmpty()) {
    viewModel.OTPApiCall(numTel)
    navigateToOtpActivity(name, lastName, password, numTel, dateNaiss)
}else{
    Toast.makeText(requireContext(), "Veuillez Remplir touts les champs", Toast.LENGTH_LONG).show()
}
        }
    }
    private fun showDatePicker() {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
                binding.tvSelectedDate.text = "Selected Date: $formattedDate"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }

    private fun navigateToOtpActivity(
        name: String,
        lastName: String,
        password: String,
        numTel: String,
        dateNaiss: String
    ) {
        val intent = Intent(context, OtpActivity::class.java).apply {
            putExtra("name", name)
            putExtra("lastName", lastName)
            putExtra("password", password)
            putExtra("numTel", numTel)
            putExtra("dateNaiss", dateNaiss)
        }
        startActivity(intent)
    }


}