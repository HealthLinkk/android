package tn.pdm.RecycleConnect.data.viewmodels

import EventsViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tn.pdm.RecycleConnect.data.repositories.EventsRepository

class EventsViewModelFactory(private val repository: EventsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventsViewModel::class.java)) {
            return EventsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
