package com.esprit.healthlink.data.model

import com.esprit.healthlink.network.PostEndPoint
import com.esprit.healthlink.network.RetrofitClient
import retrofit2.Call

class CommentRepository {
    private val retrofit = RetrofitClient.getRetrofitInstance().create(PostEndPoint::class.java)

    suspend fun getCommentsByPostId(postId: String): List<Comment> {
        return retrofit.getCommentsByPostId(postId)
    }


}
