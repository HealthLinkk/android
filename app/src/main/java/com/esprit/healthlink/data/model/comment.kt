package com.esprit.healthlink.data.model



data class Comment(val content: String, val postId: String, val userId: String, val date: String)

data class CommentResponse(val comment: Comment)

data class CommentRequest(val content: String, val postId: String, val userId: String)

