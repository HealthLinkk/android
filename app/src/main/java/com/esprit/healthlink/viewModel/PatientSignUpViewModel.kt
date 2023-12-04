package com.esprit.healthlink.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.esprit.healthlink.data.model.Message
import com.esprit.healthlink.data.model.SendOtpRequest
import com.esprit.healthlink.data.model.PatientSignupRequest
import com.esprit.healthlink.data.model.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientSignUpViewModel(private val repository: UserRepository) : ViewModel() {


    var isSignUpSuccessful: Boolean = false
    fun OTPApiCall(numTel: String) {
        // Assuming SendOtpRequest is a data class representing the request body
        val sendOtpRequest = SendOtpRequest(numTel = numTel)

        repository.sendOtp(sendOtpRequest).enqueue(object : Callback<Message> {
            override fun onResponse(
                call: Call<Message>,
                response: Response<Message>
            ) {
                if (response.isSuccessful) {
                    // Handle the successful response
                    val responseBody = response.body()
                    Log.e("PatientSignUpViewModel", " Patient Sign Up success. Code: ${responseBody}") // Process the responseBody
                } else {
                    Log.e("PatientSignUpViewModel", " Patient Sign Up Failed. error: ${response.body()}") // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<Message>, t: Throwable) {

                Log.e("PatientSignUpViewModel", " OTP API call failed", t)
            }
        })
    }

    fun PatientSignUpApiCall(
        name: String,
        lastName: String,
        password: String,
        numTel: String,
        dateNaiss: String,
        otp: String
    ) {
        // Assuming PatientSignupRequest is a data class representing the request body
        val patientSignupRequest = PatientSignupRequest(
            name = name,
            lastName = lastName,
            password = password,
            numTel = numTel,
            dateNaiss = dateNaiss,
            otp = otp
        )

        repository.patientSignup(patientSignupRequest)
            .enqueue(object : Callback<Message> {
                override fun onResponse(
                    call: Call<Message>,
                    response: Response<Message>
                ) {
                    if (response.isSuccessful) {
                        // Handle the successful response
                        val responseBody = response.body()
                        isSignUpSuccessful = (response.code() == 200)
                        Log.e("PatientSignUpViewModel", " Patient Sign Up success. Code: ${responseBody}") // Process the responseBody
                    } else {
                        Log.e("PatientSignUpViewModel", " Patient Sign Up failed. error: $name")
                        Log.e("PatientSignUpViewModel", " Patient Sign Up failed. error: $otp")
                    }
                }

                override fun onFailure(call: Call<Message>, t: Throwable) {
                    // Handle failure
                    Log.e("PatientSignUpViewModel", " Patient Sign Up API call failed", t)
                }
            })
    }
}