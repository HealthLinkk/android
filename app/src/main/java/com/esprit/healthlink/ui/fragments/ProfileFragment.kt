package com.esprit.healthlink.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.esprit.healthlink.databinding.FragmentBoardingBinding
import com.esprit.healthlink.databinding.FragmentProfileBinding
import com.esprit.healthlink.ui.AppointmentsActivity
import com.esprit.healthlink.ui.ProfileEditActivity
import com.esprit.healthlink.R

class ProfileFragment: Fragment() {
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        binding.etPersonalInform.setOnClickListener {
            val myIntent = Intent(context, ProfileEditActivity::class.java)
            startActivity(myIntent)
        }

        binding.btnreturn.setOnClickListener {

            findNavController().popBackStack()
        }
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Assuming you have a NavController for navigation
        binding.txtMyAppointment.setOnClickListener {
            val intent = Intent(requireContext(), AppointmentsActivity::class.java)
            startActivity(intent)
        }


    }
}