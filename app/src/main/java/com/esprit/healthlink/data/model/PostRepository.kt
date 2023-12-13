package com.esprit.healthlink.data.model

import com.esprit.healthlink.network.PostEndPoint
import com.esprit.healthlink.network.RetrofitClient
import com.esprit.healthlink.network.UserApiEndPoint
import retrofit2.Call

class PostRepository() {
    private val retrofit = RetrofitClient.getRetrofitInstance().create(PostEndPoint::class.java)

    fun AddPost(requestBody: PostRequest): Call<Message> {
        return retrofit.AddPost(requestBody)
    }
    fun getPosts() = retrofit.getPosts()
}