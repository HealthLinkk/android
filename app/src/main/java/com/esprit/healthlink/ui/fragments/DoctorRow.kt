package com.esprit.healthlink.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.esprit.healthlink.R
import com.esprit.healthlink.databinding.ListDoctorsBinding
import androidx.appcompat.widget.AppCompatButton

class ListDoctors: Fragment() {
    private lateinit var binding: ListDoctorsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = ListDoctorsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}