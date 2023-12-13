package com.esprit.healthlink.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.esprit.healthlink.data.model.PostRepository
import com.esprit.healthlink.databinding.FargmentAddPostBinding
import com.esprit.healthlink.viewModel.PostViewModel
import com.esprit.healthlink.viewModel.PostViewModelFactory

class PostAddFragment : Fragment() {
    lateinit var binding: FargmentAddPostBinding
    private lateinit var viewModel: PostViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        binding = FargmentAddPostBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, PostViewModelFactory(PostRepository()))[PostViewModel::class.java]



        setupUi()
        return binding.root

    }
    @SuppressLint("SuspiciousIndentation")
    private fun setupUi() {
        binding.add.setOnClickListener {
            // Get the values from your UI fields
            val title = binding.textTitle.text.toString()
            val content = binding.textContent.text.toString()


                viewModel.AddPostApiCall(title,content)

        }
    }
}