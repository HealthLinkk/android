package tn.pdm.RecycleConnect.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tn.pdm.RecycleConnect.R
import tn.pdm.RecycleConnect.data.repositories.NewsRepository
import tn.pdm.RecycleConnect.data.viewmodels.NewsViewModel
import tn.pdm.RecycleConnect.data.viewmodels.NewsViewModelFactory
import tn.pdm.RecycleConnect.databinding.ActivityMainBinding
import tn.pdm.RecycleConnect.ui.fragments.EventsFragment
import tn.pdm.RecycleConnect.ui.fragments.MedicalRecordFragment
import tn.pdm.RecycleConnect.ui.fragments.NewsFragment

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding

    //scheduleNewsScraping
    private lateinit var newsviewModel: NewsViewModel
    private val newsrepository = NewsRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.toolbarid.bottombar
        binding.MyMedicalRecord.setOnClickListener {
            navigateTo(OrgEventsActivity::class.java)
        }




        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    // Handle Home item click navigate to MainActivity
                    navigateTo(MainActivity::class.java)
                    true
                }
                R.id.navigation_article -> {
                    // Handle Article item click
                    true
                }
                R.id.navigation_events -> {
                    changeFragment(EventsFragment(), "")
                    true
                }
                R.id.navigation_profile -> {
                    // navigate to AddEventActivity
                    navigateTo(AddMedicalRecordActivity::class.java)
                    true
                }
                R.id.navigation_medical_record-> {
                    // navigate to fragment NewsFragment
                    changeFragment(MedicalRecordFragment(), "")
                    true
                }
                else -> false
            }
        }

    }
    //change fragment
    private fun changeFragment(fragment: Fragment, name: String) {

        if (name.isEmpty())
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        else
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(name)
                .commit()

    }
    private fun navigateTo(destinationActivity: Class<*>) {
        val intent = Intent(this@MainActivity, destinationActivity)
        startActivity(intent)
    }


}