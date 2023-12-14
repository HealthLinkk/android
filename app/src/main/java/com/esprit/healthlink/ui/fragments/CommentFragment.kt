// FragmentComment.kt
package com.esprit.healthlink.ui.fragments

import CommentViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.esprit.healthlink.data.adapter.CommentAdapter
import com.esprit.healthlink.data.model.CommentRepository
import com.esprit.healthlink.databinding.FragmentCommentBinding
import com.esprit.healthlink.viewModel.CommentViewModel


class FragmentComment : Fragment() {

    private lateinit var binding: FragmentCommentBinding
    private lateinit var commentViewModel: CommentViewModel
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        // Initialize CommentRepository
        val commentRepository = CommentRepository()

        // Initialize ViewModel with CommentRepository
        commentViewModel = ViewModelProvider(
            this,
            CommentViewModelFactory(commentRepository)
        )[CommentViewModel::class.java]

        // Setup RecyclerView
        commentAdapter = CommentAdapter()
        binding.recyclerViewComments.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = commentAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        // Observe changes in comments
        commentViewModel.comments.observe(viewLifecycleOwner, Observer { comments ->
            comments?.let {
                commentAdapter.setData(it)
            }
        })

        return binding.root
    }

    // Assume this function is used to set the post ID for fetching comments
    fun setPostId(postId: String) {
        // Fetch comments based on the post ID
        commentViewModel.getCommentsByPostId(postId)
    }
}
