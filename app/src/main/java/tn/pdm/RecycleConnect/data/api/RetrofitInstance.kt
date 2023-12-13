package tn.pdm.RecycleConnect.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.pdm.RecycleConnect.data.models.MedicalRecord

object RetrofitInstance {

    private const val BASE_URL = " http://172.18.7.131:9091/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val api: EventsApiService by lazy {
        retrofit.create(EventsApiService::class.java)
    }
    val apinews: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
    val apimedicalrecord: MedicalRecordApiService by lazy {
        retrofit.create(MedicalRecordApiService::class.java)
    }
}
