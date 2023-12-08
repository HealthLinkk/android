package com.esprit.healthlink.data.model

import com.esprit.healthlink.network.RetrofitClient
import com.esprit.healthlink.network.AppointmentApiEndPoint
import retrofit2.Call

class AppointmentRepository() {

    private val retrofit = RetrofitClient.getRetrofitInstance().create(AppointmentApiEndPoint::class.java)

    fun getScheduledRendezVous() = retrofit.getScheduledRendezVous()
    fun getCompletedRendezVous() = retrofit.getCompletedRendezVous()
    fun getCanceledRendezVous() = retrofit.getCanceledRendezVous()
    fun createRendezVous(requestBody: createRendezVous): Call<Message> {
        return retrofit.createRendezVous(requestBody)
    }


}