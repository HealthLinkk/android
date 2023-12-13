package tn.pdm.RecycleConnect.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tn.pdm.RecycleConnect.data.models.Events
import tn.pdm.RecycleConnect.databinding.SingleItemEventBinding

    class EventsAdapter(
    var eventsList: List<Events>,
    private val onItemClick: (Events) -> Unit
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>(),Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleItemEventBinding.inflate(inflater, parent, false)
        return EventViewHolder(binding, onItemClick)
    }
        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val filteredList = mutableListOf<Events>()

                    if (constraint.isNullOrEmpty()) {
                        filteredList.addAll(eventsList)
                    } else {
                        val filterPattern = constraint.toString().toLowerCase().trim()

                        for (event in eventsList) {
                            if (event.nameEvent.toLowerCase().contains(filterPattern)) {
                                filteredList.add(event)
                            }
                        }
                    }

                    val results = FilterResults()
                    results.values = filteredList
                    return results
                }


                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    eventsList = results?.values as List<Events>
                    notifyDataSetChanged()
                }
            }
        }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventsList[position]
        holder.bind(event)
        Log.d("EventsAdapter", "onBindViewHolder: Event at position $position bound.")
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    class EventViewHolder(
        private val binding: SingleItemEventBinding,
        private val onItemClick: (Events) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Events) {
            Log.d("EventsAdapter", "Binding event: $event")
            binding.eventName.text = event.nameEvent
            binding.eventDescription.text = event.descriptionEvent

            // Load the event image
            Glide.with(binding.root.rootView.context)
                .load(event.PhotoEvent)
                .into(binding.eventImage)

            // Set click listener on the item view and pass the event id to the click listener
            binding.root.setOnClickListener {
                Log.d("EventsAdapter", "Item clicked. Event ID: ${event._id}")
                onItemClick(event)
            }
        }
    }
}
