package tn.pdm.RecycleConnect.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tn.pdm.RecycleConnect.data.models.Events
import tn.pdm.RecycleConnect.databinding.SingleItemEventbyorgBinding

class EventsByOrgAdapter(
    var eventsList: List<Events>,
    private val onItemClick: (Events) -> Unit
) : RecyclerView.Adapter<EventsByOrgAdapter.EventByOrgViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventByOrgViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleItemEventbyorgBinding.inflate(inflater, parent, false)
        return EventByOrgViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: EventByOrgViewHolder, position: Int) {
        val event = eventsList[position]
        holder.bind(event)
        Log.d("EventsByOrgAdapter", "onBindViewHolder: Event at position $position bound.")
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    class
    EventByOrgViewHolder(
        private val binding: SingleItemEventbyorgBinding,
        private val onItemClick: (Events) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Events) {
            Log.d("EventsByOrgAdapter", "Binding event: $event")
            binding.eventName.text = event.nameEvent

            // Load the event image
            Glide.with(binding.root.rootView.context)
                .load(event.PhotoEvent)
                .into(binding.eventImage)

            // Set click listener on the item view and pass the event id to the click listener
            binding.root.setOnClickListener {
                Log.d("EventsByOrgAdapter", "Item clicked. Event ID: ${event._id}")
                onItemClick(event)
            }
        }
    }
}