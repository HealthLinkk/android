@file:Suppress("DEPRECATION")

package tn.pdm.RecycleConnect.ui.fragments

import EventsViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tn.pdm.RecycleConnect.data.repositories.EventsRepository
import tn.pdm.RecycleConnect.data.viewmodels.EventsViewModelFactory
import tn.pdm.RecycleConnect.databinding.FragmentEventsBinding
import tn.pdm.RecycleConnect.ui.activities.DetailsEvent
import tn.pdm.RecycleConnect.ui.adapters.EventsAdapter


const val TAG = "EventsFragment"

class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private lateinit var viewModel: EventsViewModel
    private val repository = EventsRepository()
    //init search bar


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(layoutInflater)
        // instantiate the EventsViewModel
        viewModel = ViewModelProvider(this, EventsViewModelFactory(repository))
            .get(EventsViewModel::class.java)
        val adapter = EventsAdapter(emptyList()) { clickedEvent ->
            clickedEvent._id?.let { eventId ->
                Log.d(TAG, "Item clicked. Event ID: $eventId")
                // Handle the item click, navigate to DetailsEventActivity with the clicked event data
                val intent = Intent(requireContext(), DetailsEvent::class.java)
                intent.putExtra("eventId", eventId)
                intent.putStringArrayListExtra("interested", ArrayList(clickedEvent.interested))
                intent.putStringArrayListExtra("going", ArrayList(clickedEvent.going))
                startActivity(intent)
            }
            //pass interested: List<String> to the DetailsEventActivity

        }

        binding.rvEvents.adapter = adapter

        binding.searchView
            .getEditText()
            .setOnEditorActionListener { v, actionId, event ->
                binding.searchBar.setText(binding.searchView.getText())
                binding.searchView.hide()
                false
            }

        // Search bar functionality (filtering)
        // Observe the LiveData from the ViewModel
        viewModel.events.observe(viewLifecycleOwner, Observer { events ->
            Log.d(TAG, "onChanged: Events size - ${events.size}")
            adapter.eventsList = events
            adapter.notifyDataSetChanged()
        })


        viewModel.getAllEvents()
        setHasOptionsMenu(true)
        binding.rvEvents.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root


    }



}