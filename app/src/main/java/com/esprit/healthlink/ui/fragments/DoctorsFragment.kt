package com.esprit.healthlink.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.annotation.BoolRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.navigation.fragment.findNavController

import com.esprit.healthlink.databinding.FragmentDoctorsBinding
import com.esprit.healthlink.viewModel.DoctorsViewModel
import com.esprit.healthlink.viewModel.DoctorsViewModelFactory

class DoctorsFragment : Fragment() {
    private lateinit var binding: FragmentDoctorsBinding

    private lateinit var doctorsViewModel: DoctorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDoctorsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner  // Important for LiveData to observe correctly

        // Initialize doctorsViewModel before using it
        doctorsViewModel = ViewModelProvider(this, DoctorsViewModelFactory())[DoctorsViewModel::class.java]
        binding.viewModel = doctorsViewModel
        binding.btnreturn.setOnClickListener {

        }
        /*
        val toolbar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        // Enable the back button
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = doctorsViewModel.getAdapter() // Set the adapter here
            val decoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            addItemDecoration(decoration)

            
        }

        initObserver()
    }

    private fun initObserver() {
        doctorsViewModel.doctorsData.observe(viewLifecycleOwner, Observer { doctors ->
            if (doctors != null) {
                binding.progressbar.visibility = View.INVISIBLE
                doctorsViewModel.setAdapterData(ArrayList(doctors))
            } else {
                Toast.makeText(requireContext(), "Error Fetching Data", Toast.LENGTH_LONG).show()
            }
        })
    }


}

