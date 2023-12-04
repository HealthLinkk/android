package com.esprit.healthlink.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.esprit.healthlink.data.model.UserRepository

class PatientSignUpViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PatientSignUpViewModel(
            repository = UserRepository()
        ) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}