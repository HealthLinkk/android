package com.esprit.healthlink.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

import com.esprit.healthlink.R
import com.esprit.healthlink.databinding.ActivityAppointmentsBinding
import com.esprit.healthlink.ui.fragments.UpcomingFragment
import com.esprit.healthlink.ui.fragments.CompletedFragment
import com.esprit.healthlink.ui.fragments.CanceledFragment

class AppointmentsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentsBinding.inflate(layoutInflater)

        setContentView(binding.root)


        // Enable the back button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
       // showUpcomingFragment()


        binding.btnUpcoming.setOnClickListener {
            showFragment(UpcomingFragment())
        }

        binding.btnCompleted.setOnClickListener {
            showFragment(CompletedFragment())
            showFragment(CompletedFragment())
        }
        binding.btnCanceled.setOnClickListener {
            showFragment(CanceledFragment())
        }

    }

    private fun showFragment( frag :Fragment) {
        val transaction = supportFragmentManager.beginTransaction()


        transaction.replace(R.id.AppointmentFragment, frag)
        transaction.commit()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // This will simulate a back button press
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Handle the back button press
    override fun onBackPressed() {
        // Perform any additional cleanup or logic
        super.onBackPressed()
    }

}
