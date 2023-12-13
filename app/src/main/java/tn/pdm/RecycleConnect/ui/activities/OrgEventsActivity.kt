package tn.pdm.RecycleConnect.ui.activities

import EventsViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tn.pdm.RecycleConnect.R
import tn.pdm.RecycleConnect.data.repositories.EventsRepository
import tn.pdm.RecycleConnect.data.viewmodels.EventsViewModelFactory
import tn.pdm.RecycleConnect.databinding.ActivityOrgEventsBinding
import tn.pdm.RecycleConnect.ui.adapters.EventsByOrgAdapter

class OrgEventsActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrgEventsBinding
    private lateinit var viewModel: EventsViewModel
    private val repository = EventsRepository()
    private lateinit var eventsAdapter: EventsByOrgAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_org_events)
        binding = ActivityOrgEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener {
            finish()
        }

        eventsAdapter = EventsByOrgAdapter(emptyList()) {   clickedEvent ->
            clickedEvent._id?.let { eventId ->
                Log.d("OrgEvents", "Item clicked. Event ID: $eventId")
                val intent = Intent(this, EditEventOrgActivity::class.java)
                intent.putExtra("eventId", eventId)
                startActivity(intent)
            }

        }
        binding.rvEventsByOrg.adapter = eventsAdapter
        binding.rvEventsByOrg.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this, EventsViewModelFactory(repository))
            .get(EventsViewModel::class.java)

        // Observe the LiveData in the ViewModel
        viewModel.events.observe(this, Observer { events ->
            // Update the adapter with the new list of events
            eventsAdapter.eventsList = events
            eventsAdapter.notifyDataSetChanged()
        })


        // Call the getAllEvents function to fetch events
        viewModel.getAllEvents()
    }
}