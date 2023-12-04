package com.esprit.healthlink.data.model

import com.esprit.healthlink.network.RetrofitClient
import com.esprit.healthlink.network.UserApiEndPoint
import retrofit2.Call

class UserRepository() {

    private val retrofit = RetrofitClient.getRetrofitInstance().create(UserApiEndPoint::class.java)

    fun getDoctors() = retrofit.getDoctors()
    fun patientSignup(requestBody: PatientSignupRequest): Call<Message> {
        return retrofit.PatientSignup(requestBody)
    }
    fun sendOtp(requestBody: SendOtpRequest): Call<Message> {
        return retrofit.SendOtp(requestBody)
    }
    fun Login(requestBody: LoginRequest): Call<User> {
        return retrofit.Login(requestBody)
    }


}