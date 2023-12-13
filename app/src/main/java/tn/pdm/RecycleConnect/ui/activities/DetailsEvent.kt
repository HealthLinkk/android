package tn.pdm.RecycleConnect.ui.activities

import EventsViewModel
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import tn.pdm.RecycleConnect.data.models.Events
import tn.pdm.RecycleConnect.data.repositories.EventsRepository
import tn.pdm.RecycleConnect.data.viewmodels.EventsViewModelFactory
import tn.pdm.RecycleConnect.databinding.ActivityDetailsEventBinding
const val _TAG = "DetailsEvent"

class DetailsEvent : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsEventBinding
    private lateinit var viewModel: EventsViewModel
    private val repository = EventsRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, EventsViewModelFactory(repository))
            .get(EventsViewModel::class.java)

        // Floating button to go back to the previous screen
        binding.floatingActionButton.setOnClickListener {
            finish()
        }

        val interestedList = intent.getStringArrayListExtra("interested")
        val goingList = intent.getStringArrayListExtra("going")
        //Log interested and going lists
        val interestedCount: Int = interestedList?.size ?: 0
        val goingCount: Int = goingList?.size ?: 0
        Log.d("DetailsEventActivity", "Interested count: $interestedCount")
        Log.d("DetailsEventActivity", "Going count: $goingCount")
        // Retrieve the event id from the intent
        val eventId = intent.getStringExtra("eventId")
        Log.d(_TAG, "Event ID in DetailsEvent Activity: $eventId")

        //interestedbtn call the interested function
        binding.interestedbtn.setOnClickListener {
            if (eventId != null) {
                viewModel.interestedEvent(eventId)
            }
        }
        binding.goingbtn.setOnClickListener {
            if (eventId != null) {
                viewModel.goingEvent(eventId)
            }
        }

        // Call the getEventById function and observe the LiveData
        if (eventId != null) {
            viewModel.getEventById(eventId)
        }

        // Observe the event LiveData for changes
        viewModel.event.observe(this) { event ->
            // Update UI components with the event details
            updateUI(event)
        }

        //log event id
        Log.d(_TAG, "onCreate: $eventId")


    }

    private fun updateUI(event: Events?) {
        // Check if the event is not null before updating UI components
        event?.let {
            binding.eventDName.text = event.nameEvent
            binding.eventDate.text = it.startEvent.toString() // Adjust as needed
            binding.eventDAdress.text = event.addressEvent
            binding.eventDescription.text = event.descriptionEvent
            binding.eventDInterested.text = event.interested.size.toString()
            binding.eventIDGoing.text = event.going.size.toString()

            Glide.with(binding.root.rootView.context)
                .load(event.PhotoEvent)
                .into(binding.eventImage)
            //log event attributes
            Log.d(_TAG, "updateUI: ${event.nameEvent}")
            Log.d(_TAG, "updateUI: ${event.startEvent}")
            Log.d(_TAG, "updateUI: ${event.addressEvent}")
            Log.d(_TAG, "updateUI: ${event.descriptionEvent}")
            Log.d(_TAG, "updateUI: ${event.PhotoEvent}")

        } ?: run {
            // Handle the case where the event is null (optional)
            Log.e(_TAG, "Event object is null")
        }
    }


}