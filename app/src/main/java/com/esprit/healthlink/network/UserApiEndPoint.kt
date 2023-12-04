package com.esprit.healthlink.network


import retrofit2.Call

import com.esprit.healthlink.data.model.Doctor
import com.esprit.healthlink.data.model.LoginRequest
import com.esprit.healthlink.data.model.Message
import com.esprit.healthlink.data.model.PatientSignupRequest
import com.esprit.healthlink.data.model.SendOtpRequest
import com.esprit.healthlink.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface UserApiEndPoint {
    @GET("rdv/Doctors")
    fun getDoctors(): Call<List<Doctor>>

    @POST("users/PatientSignUp")
    fun PatientSignup(
        @Body requestBody: PatientSignupRequest
    ): Call<Message>


    @POST("users/sendOTP")
    fun SendOtp( @Body requestBody: SendOtpRequest): Call<Message>

    @POST("users/login")
    fun Login (@Body requestBody: LoginRequest) : Call<User>


}