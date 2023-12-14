package com.esprit.healthlink.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.esprit.healthlink.R
import com.esprit.healthlink.databinding.FragmentHomeBinding


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

        return binding.root
    }


}