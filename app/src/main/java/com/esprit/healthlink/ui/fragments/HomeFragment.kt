package com.esprit.healthlink.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.esprit.healthlink.R
import com.esprit.healthlink.databinding.FragmentHomeBinding
import com.esprit.healthlink.databinding.FragmentSignUpDoctorBinding
import com.esprit.healthlink.ui.ConnexionActivity
import com.esprit.healthlink.ui.ForgetPasswordActivity

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)


        binding.btnConsulter.setOnClickListener {
            findNavController().navigate(R.id.doctorsFragment,null, navOptions {
                popUpTo(R.id.item_home, popUpToBuilder = {
                    inclusive = true
                })
            })
        }
        binding.btnIconOne.setOnClickListener {
            findNavController().navigate(R.id.postsFragment,null, navOptions {
                popUpTo(R.id.item_home, popUpToBuilder = {
                    inclusive = true
                })
            })
        }

        return binding.root
    }


}