package com.esprit.healthlink.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.esprit.healthlink.data.model.LoginRequest
import com.esprit.healthlink.data.model.Message
import com.esprit.healthlink.data.model.SendOtpRequest
import com.esprit.healthlink.data.model.User
import com.esprit.healthlink.data.model.UserRepository
import com.esprit.healthlink.network.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel(private val repository: UserRepository,private val sessionManager: SessionManager):ViewModel() {

var isSignInSuccessful: Boolean = false
    fun LoginApiCall(numTel: String , password :String) {
        // Assuming SendOtpRequest is a data class representing the request body
        val LoginRequest = LoginRequest(numTel = numTel, password = password)

        repository.Login(LoginRequest).enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    // Handle the successful response
                    val user = response.body()
                    val cookies = response.headers().values("Set-Cookie")
                    val jwtCookie = cookies.find { it.startsWith("jwt=") }
                    val jwtToken = jwtCookie?.substringAfter("jwt=")
                    sessionManager.user = user
                    isSignInSuccessful = (response.code() == 200)
                    // Store the token in SessionManager
                    sessionManager.authToken = jwtToken
                    Log.e("SignInViewModel", " Login succeded : $jwtToken , $user ")
                    Log.e("SignInViewModel", " Login succeded : $sessionManager , $user ") // Handle unsuccessful response



                } else {
                    Log.e("SignInViewModel", " Login Failed. error: ${response.body()}") // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {

                Log.e("SignInViewModel", " Login failed", t)
            }
        })
    }
}