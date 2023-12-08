// UpcomingFragment.kt
package com.esprit.healthlink.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import com.esprit.healthlink.databinding.FragmentDoctorsBinding
import com.esprit.healthlink.databinding.FragmentUpcomingappointmentsBinding

import com.esprit.healthlink.viewModel.DoctorsViewModel
import com.esprit.healthlink.viewModel.AppointmentsViewModel
import com.esprit.healthlink.viewModel.DoctorsViewModelFactory
import com.esprit.healthlink.viewModel.AppointmentsViewModelFactory
import com.esprit.healthlink.data.adapter.AppointmentAdapter


class UpcomingFragment : Fragment() {
    private lateinit var binding: FragmentUpcomingappointmentsBinding

    private lateinit var appointmentsViewModel: AppointmentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpcomingappointmentsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner  // Important for LiveData to observe correctly

        // Initialize doctorsViewModel before using it
        appointmentsViewModel = ViewModelProvider(this, AppointmentsViewModelFactory())[AppointmentsViewModel::class.java]
        binding.viewModel = appointmentsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.rvUpcoming.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = appointmentsViewModel.getAdapter() // Set the adapter here
            val decoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }

        initObserver()
    }

    private fun initObserver() {
        appointmentsViewModel.appointmentsData.observe(viewLifecycleOwner, Observer { appointments ->
            if (appointments != null) {
                binding.progressbar.visibility = View.INVISIBLE
                appointmentsViewModel.setAdapterData(ArrayList(appointments))
            } else {
                Toast.makeText(requireContext(), "Error Fetching Data", Toast.LENGTH_LONG).show()
            }
        })
    }


}