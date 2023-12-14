package com.esprit.healthlink.network

import ReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{


        fun getRetrofitInstance(): Retrofit {

            val baseUrl = "http://192.168.43.254:54013/"

                return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

        }
    }
}