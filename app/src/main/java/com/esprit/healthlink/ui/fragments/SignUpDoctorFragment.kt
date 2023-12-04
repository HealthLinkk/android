package com.esprit.healthlink.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.esprit.healthlink.databinding.FragmentSignUpBinding
import com.esprit.healthlink.databinding.FragmentSignUpDoctorBinding

class SignUpDoctorFragment: Fragment() {
    private lateinit var binding: FragmentSignUpDoctorBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentSignUpDoctorBinding.inflate(layoutInflater)



        return binding.root
    }
}