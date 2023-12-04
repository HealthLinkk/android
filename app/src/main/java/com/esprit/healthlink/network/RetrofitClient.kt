package com.esprit.healthlink.network

import ReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{


        fun getRetrofitInstance(): Retrofit {

            val baseUrl = "http://192.168.1.13:35873/"

                return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

        }
    }
}