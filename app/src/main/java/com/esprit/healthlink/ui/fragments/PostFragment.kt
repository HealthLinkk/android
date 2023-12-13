package com.esprit.healthlink.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.esprit.healthlink.R
import com.esprit.healthlink.data.model.PostRepository

import com.esprit.healthlink.databinding.FragmentPostsBinding

import com.esprit.healthlink.viewModel.PostViewModel
import com.esprit.healthlink.viewModel.PostViewModelFactory

class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding
    private lateinit var postViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        postViewModel = ViewModelProvider(this, PostViewModelFactory(PostRepository()))[PostViewModel::class.java]

        binding.viewModel = postViewModel

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.postAddFragment,null, navOptions {
                popUpTo(R.id.item_home, popUpToBuilder = {
                    inclusive = true
                })
            })
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postViewModel.getAdapter() // Set the adapter here
            val decoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }

        initObserver()
    }

    private fun initObserver() {
        postViewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
            if (posts != null) {
                binding.progressbar.visibility = View.INVISIBLE
                postViewModel.setAdapterData(ArrayList(posts))
            } else {
                Toast.makeText(requireContext(), "Error Fetching Data", Toast.LENGTH_LONG).show()
            }
        })
    }
}
