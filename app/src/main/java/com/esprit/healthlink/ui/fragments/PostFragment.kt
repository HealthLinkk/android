package com.esprit.healthlink.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.esprit.healthlink.data.model.PostRepository
import com.esprit.healthlink.databinding.FragmentItemPostBinding

import com.esprit.healthlink.viewModel.PostViewModel
import com.esprit.healthlink.viewModel.PostViewModelFactory

class PostFragment : Fragment() {

    private lateinit var binding: FragmentItemPostBinding
    private lateinit var postViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemPostBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        postViewModel = ViewModelProvider(this, PostViewModelFactory(PostRepository()))[PostViewModel::class.java]


        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        val postAdapter = postViewModel.getAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        initObserver()

        // Trigger the API call to fetch posts
        postViewModel.getPosts()
    }

    private fun initObserver() {
        postViewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
            if (posts != null) {

                postViewModel.getAdapter().setData(ArrayList(posts)) // Mise à jour de l'adaptateur avec les données
            } else {
                Toast.makeText(requireContext(), "Error Fetching Posts", Toast.LENGTH_LONG).show()
            }
        })
    }
}
