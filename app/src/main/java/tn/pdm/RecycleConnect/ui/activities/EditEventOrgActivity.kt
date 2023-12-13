package tn.pdm.RecycleConnect.ui.activities

import EventsViewModel
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import tn.pdm.RecycleConnect.R
import tn.pdm.RecycleConnect.data.models.Events
import tn.pdm.RecycleConnect.data.repositories.EventsRepository
import tn.pdm.RecycleConnect.data.viewmodels.EventsViewModelFactory
import tn.pdm.RecycleConnect.databinding.ActivityEditEventOrgBinding

class EditEventOrgActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditEventOrgBinding
    private lateinit var viewModel: EventsViewModel
    private val repository = EventsRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_event_org)
        binding = ActivityEditEventOrgBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, EventsViewModelFactory(repository))
            .get(EventsViewModel::class.java)


        binding.floatingActionButton.setOnClickListener {
            finish()
        }
        val eventId = intent.getStringExtra("eventId")
        Log.d(_TAG, "Event ID in EditEvent Activity: $eventId")

        if (eventId != null) {
            viewModel.getEventById(eventId)
        }
        viewModel.event.observe(this) { event ->
            // Update UI components with the event details
            updateUI(event)
        }
        binding.deletbtn.setOnClickListener {
            if (eventId != null) {
                viewModel.deleteEvent(eventId)
            }
            finish()
        }

    }
    private fun updateUI(event: Events?) {
        // Check if the event is not null before updating UI components
        event?.let {
            binding.textViewCurrentStartDate.text = it.startEvent.toString() // Adjust as needed
            binding.textViewCurrentEndDate.text = it.endEvent.toString() // Adjust as needed

            Glide.with(binding.root.rootView.context)
                .load(event.PhotoEvent)
                .into(binding.eventImage)
            //log event attributes
            Log.d("EditEvent", "updateUI: ${event.startEvent}")
            Log.d("EditEvent", "updateUI: ${event.endEvent}")
            Log.d("EditEvent", "updateUI: ${event.PhotoEvent}")

        } ?: run {
            // Handle the case where the event is null (optional)
            Log.e("EditeventOrg", "Event object is null")
        }
    }


}