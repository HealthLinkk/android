package com.esprit.healthlink.network

import android.content.Context
import android.content.SharedPreferences
import com.esprit.healthlink.data.model.User

class SessionManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        private const val KEY_TOKEN = "token"
    }

    var authToken: String?
        get() = sharedPreferences.getString(KEY_TOKEN, null)
        set(token) {
            editor.putString(KEY_TOKEN, token)
            editor.apply()
        }
    var user: User? = null
        set(value) {
            field = value
            // Save user details to SharedPreferences or any other storage mechanism
            saveUserDetails(value)
        }
        get() {
            // Retrieve user details from SharedPreferences or any other storage mechanism
            return loadUserDetails()
        }

    private fun saveUserDetails(user: User?) {
        // Implement the code to save user details to SharedPreferences or any other storage mechanism
    }

    private fun loadUserDetails(): User? {
        // Implement the code to load user details from SharedPreferences or any other storage mechanism
        return null
    }

    fun clearAuthToken() {
        editor.remove(KEY_TOKEN)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return authToken != null
    }
}
