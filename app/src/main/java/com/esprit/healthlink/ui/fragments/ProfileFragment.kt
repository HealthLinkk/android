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
import com.esprit.healthlink.ui.ProfileEditActivity

class ProfileFragment: Fragment() {
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
binding.etPersonalInform.setOnClickListener {
    val myIntent = Intent(context, ProfileEditActivity::class.java)
    startActivity(myIntent)
}
        binding.btnreturn.setOnClickListener{

            findNavController().popBackStack()
        }
        return binding.root

    }
}