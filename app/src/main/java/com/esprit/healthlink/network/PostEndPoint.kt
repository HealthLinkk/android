package com.esprit.healthlink.network

import com.esprit.healthlink.data.model.Message
import com.esprit.healthlink.data.model.PatientSignupRequest
import com.esprit.healthlink.data.model.Post
import com.esprit.healthlink.data.model.PostRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostEndPoint {
    @POST("post/Cposts")
    fun AddPost(
        @Body requestBody: PostRequest
    ): Call<Message>
@GET("post/Aposts")
    fun getPosts(): Call<List<Post>>
}