package com.esprit.healthlink.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.esprit.healthlink.databinding.FragmentSignInBinding
import com.esprit.healthlink.databinding.FragmentSignUpBinding
import com.esprit.healthlink.network.SessionManager
import com.esprit.healthlink.ui.DoctorSignInActivity
import com.esprit.healthlink.ui.ForgetPasswordActivity
import com.esprit.healthlink.ui.HomepageActivity
import com.esprit.healthlink.ui.OtpActivity
import com.esprit.healthlink.viewModel.SignInViewModel
import com.esprit.healthlink.viewModel.SignInViewModelFactory

class SignInFragment : Fragment(){
    private lateinit var binding:FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel
    private val sessionManager: SessionManager by lazy { SessionManager(requireContext()) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)

        binding.btnForget.setOnClickListener {
            val intent = Intent(context, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
        binding.btnDoctor.setOnClickListener {
            val intent = Intent(context, DoctorSignInActivity::class.java)
            startActivity(intent)
        }
        viewModel = ViewModelProvider(this,SignInViewModelFactory(requireContext()))[SignInViewModel::class.java]
        SetupUi()

        return binding.root
    }

    private fun SetupUi(){
        binding.btnLogin.setOnClickListener{
            val password = binding.Password.text.toString()
            val numTel = binding.NumTel.text.toString()
            if (numTel.isNotEmpty() && password.isNotEmpty()) {
                viewModel.LoginApiCall(numTel,password)

                if(viewModel.isSignInSuccessful) {
                    navigateToHome()
                }
            }
        }
    }
    private fun navigateToHome(){
        val intent = Intent(context, HomepageActivity::class.java)
        startActivity(intent)
    }
}