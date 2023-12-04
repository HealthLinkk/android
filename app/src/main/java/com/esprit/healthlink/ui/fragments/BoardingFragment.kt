package com.esprit.healthlink.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.esprit.healthlink.R
import com.esprit.healthlink.databinding.FragmentBoardingBinding
import com.esprit.healthlink.databinding.FragmentSignUpBinding

class BoardingFragment : Fragment() {
    private lateinit var binding: FragmentBoardingBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentBoardingBinding.inflate(layoutInflater)



        binding.btnGetStarted.setOnClickListener {
            showSignUpDoctorFragment()
        }

        return binding.root

    }
    @SuppressLint("SuspiciousIndentation")
    private fun showSignUpDoctorFragment() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val signUpFragment = SignUpDoctorFragment()



            transaction?.replace(R.id.fragmentContainer, signUpFragment)

        transaction?.commit()
    }


}