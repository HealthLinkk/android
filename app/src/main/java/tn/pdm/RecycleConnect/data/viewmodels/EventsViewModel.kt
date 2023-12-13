// EventsViewModel.kt
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import tn.pdm.RecycleConnect.data.api.RetrofitInstance
import tn.pdm.RecycleConnect.data.models.Events
import tn.pdm.RecycleConnect.data.repositories.EventsRepository
import java.io.File

class EventsViewModel(private val repository: EventsRepository) : ViewModel() {

    // LiveData for events
    private val _events = MutableLiveData<List<Events>>()
    val events: LiveData<List<Events>> get() = _events
    //live data for a event
    private val _event = MutableLiveData<Events>()
    val event: LiveData<Events> get() = _event

    // Function to fetch events
    fun getAllEvents() {
        viewModelScope.launch {
            try {
                val eventsList = repository.getAllEvents().sortedByDescending { it.createdAt }
                Log.e("EventsViewModel", "getAllEvents: $events.size")
                _events.postValue(eventsList)
            } catch (e: Exception) {
                Log.e("EventsViewModel", "getAllEvents: ${e.message}")
                // Handle the exception
            }
        }
    }
    //delete event
    fun deleteEvent(eventId: String) {
        viewModelScope.launch {
            try {
                val message = repository.deleteEvent(eventId)
                // Handle the success or failure, maybe update UI or show a toast
                Log.d("EventsViewModel", "deleteEvent: $message")
            } catch (e: Exception) {
                Log.e("EventsViewModel", "deleteEvent: ${e.message}")
                // Handle the exception
            }
        }
    }

    // Function to fetch event by id not suspended
    fun getEventById(eventId: String) {
        viewModelScope.launch {
            try {
                val event = repository.getEventById(eventId)
                _event.postValue(event)
            } catch (e: Exception) {
                Log.e("EventsViewModel", "getEventById: ${e.message}")
                // Handle the exception
            }
        }
    }
    fun interestedEvent(eventId: String) {
        viewModelScope.launch {
            try {
                val message = repository.interestedEvent(eventId)
                // Handle the success or failure, maybe update UI or show a toast
                Log.d("EventsViewModel", "interestedEvent: $message")
            } catch (e: Exception) {
                Log.e("EventsViewModel", "interestedEvent: ${e.message}")
                // Handle the exception
            }
        }
    }




    // Add a function to create an event
    suspend fun createEvent(event: Events, imageUri: String): Events {
        val nameEvent = event.nameEvent.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionEvent = event.descriptionEvent.toRequestBody("text/plain".toMediaTypeOrNull())
        val addressEvent = event.addressEvent.toRequestBody("text/plain".toMediaTypeOrNull())
        //startEvent is a Date object, so we need to convert it to a string
        val startEvent = event.startEvent.toString().toRequestBody("text/plain".toMediaTypeOrNull())


        val imageFile = File(imageUri)
        val imageRequestBody = imageFile.asRequestBody("PhotoEvent/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("PhotoEvent", imageFile.name, imageRequestBody)


        // Make the API call with both parts
        val response = RetrofitInstance.api.createEvent(nameEvent, descriptionEvent, addressEvent, startEvent,imagePart)

        if (response.success) {
            return response.events
        } else {
            // Handle the error or return the original event object

            return event
        }
    }



    // Function to mark as going
    fun goingEvent(eventId: String) {
        viewModelScope.launch {
            try {
                val message = repository.goingEvent(eventId)
                // Handle the success or failure, maybe update UI or show a toast
                Log.d("EventsViewModel", "goingEvent: $message")
            } catch (e: Exception) {
                Log.e("EventsViewModel", "goingEvent: ${e.message}")
                // Handle the exception
            }
        }
    }


}
