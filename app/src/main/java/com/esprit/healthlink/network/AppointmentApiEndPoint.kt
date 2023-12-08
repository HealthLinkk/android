package com.esprit.healthlink.network


import retrofit2.Call

import com.esprit.healthlink.data.model.Doctor
import com.esprit.healthlink.data.model.LoginRequest
import com.esprit.healthlink.data.model.Message
import com.esprit.healthlink.data.model.PatientSignupRequest
import com.esprit.healthlink.data.model.SendOtpRequest
import com.esprit.healthlink.data.model.User
import com.esprit.healthlink.data.model.Appointment
import com.esprit.healthlink.data.model.createRendezVous
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AppointmentApiEndPoint {
    @GET("/rdv/rendezvouss/scheduled")
    fun getScheduledRendezVous(): Call<List<Appointment>>

    @GET("rdv/rendezvous/completed")
    fun getCompletedRendezVous(): Call<List<Appointment>>

    @GET("rdv/rendezvous/canceled")
    fun getCanceledRendezVous(): Call<List<Appointment>>

    @POST("rdv/")
    fun createRendezVous(
        @Body requestBody: createRendezVous
    ): Call<Message>



}