package com.esprit.healthlink.network

import com.esprit.healthlink.data.model.Comment
import com.esprit.healthlink.data.model.Message
import com.esprit.healthlink.data.model.PatientSignupRequest
import com.esprit.healthlink.data.model.Post
import com.esprit.healthlink.data.model.PostRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostEndPoint {
    @POST("post/Cposts")
    fun AddPost(
        @Body requestBody: PostRequest
    ): Call<Message>
@GET("post/Aposts")
    fun getPosts(): Call<List<Post>>
    @GET("comments/{postId}")
    suspend fun getCommentsByPostId(@Path("postId") postId: String): List<Comment>
}