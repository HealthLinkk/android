package com.esprit.healthlink.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.esprit.healthlink.data.model.UserRepository
import com.esprit.healthlink.network.SessionManager

class SignInViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(
            repository = UserRepository(),
            sessionManager = SessionManager(context)
        ) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}