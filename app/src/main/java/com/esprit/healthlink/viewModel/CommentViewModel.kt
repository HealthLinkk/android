// CommentViewModel.kt
package com.esprit.healthlink.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esprit.healthlink.data.model.Comment
import com.esprit.healthlink.data.model.CommentRepository
import kotlinx.coroutines.launch

class CommentViewModel(private val commentRepository: CommentRepository) : ViewModel() {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> get() = _comments

    init {
        // Vous pouvez initialiser d'autres choses ici si nécessaire
    }

    fun getCommentsByPostId(postId: String) {
        viewModelScope.launch {
            try {
                val comments = commentRepository.getCommentsByPostId(postId)
                _comments.value = comments
            } catch (e: Exception) {
                // Gérer les erreurs
            }
        }
    }
}
