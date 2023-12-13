package com.esprit.healthlink.data.model


data class Posts(val post: List<Post>)

data class Post(
    val _id : String,
    val title:  String,
val content: String,
val author: String,
val createdAt: String,
val updatedAt: String
)

data class PostRequest(
    val title:  String,
    val content: String,
    val author: String

)

