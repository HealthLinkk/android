package com.esprit.healthlink.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.esprit.healthlink.data.model.AppointmentRepository

class AppointmentsViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AppointmentsViewModel(
            repository = AppointmentRepository()
        ) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}