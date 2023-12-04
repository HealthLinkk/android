package com.esprit.healthlink.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.esprit.healthlink.R
import com.esprit.healthlink.databinding.ActivityConnexionBinding
import com.esprit.healthlink.databinding.ActivityHomepageBinding
import com.esprit.healthlink.ui.fragments.BoardingFragment
import com.esprit.healthlink.ui.fragments.DoctorsFragment
import com.esprit.healthlink.ui.fragments.HomeFragment
import com.esprit.healthlink.ui.fragments.SignInFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomepageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.hide()
        val navView: BottomNavigationView = binding.bottomNavigation
        val navController = findNavController(R.id.fragment_content_main)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.item_home,
            R.id.item_profile,
            R.id.doctorsFragment
            // Add more fragment IDs as needed
        ))

        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)



    }
    private fun showDoctorsInFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val showDoctorsFragment = DoctorsFragment()


        transaction.replace(R.id.fragmentContainer, showDoctorsFragment)
        transaction.commit()
    }

    private fun showHomeFragment(){
        val transaction = supportFragmentManager.beginTransaction()
        val homeFragment = HomeFragment()


        transaction.replace(R.id.fragmentContainer, homeFragment)
        transaction.commit()
    }

}