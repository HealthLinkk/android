package tn.pdm.RecycleConnect.data.repositories

// EventsRepository.kt
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import tn.pdm.RecycleConnect.data.api.RetrofitInstance
import tn.pdm.RecycleConnect.data.models.Events
import java.io.File


class EventsRepository() {
    suspend fun getAllEvents(): List<Events> {
        val response = RetrofitInstance.api.getAllEvents()
        if (response.success) {
            return response.events
        } else {
            // Handle the error or return an empty list
            return emptyList()
        }
    }

    //use a multi-part request to send the image data to the server with image
    suspend fun createEvent(event: Events, imageUri: String) {
        // Convert the nameEvent, descriptionEvent, addressEvent to JSON string and create RequestBody
        val nameEvent = event.nameEvent.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionEvent = event.descriptionEvent.toRequestBody("text/plain".toMediaTypeOrNull())
        val addressEvent = event.addressEvent.toRequestBody("text/plain".toMediaTypeOrNull())
        val startEvent = event.startEvent.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        // Create MultipartBody.Part for the image
        val imageFile = File(imageUri)
        val imageRequestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, imageRequestBody)

        // Make the API call with both parts
        val response = RetrofitInstance.api.createEvent(nameEvent, descriptionEvent, addressEvent, startEvent, imagePart)

        if (response.success) {
            // handle success
        } else {
            // handle failure
        }
    }
    // Function to fetch event by id
    suspend fun getEventById(eventId: String): Events {
        val response = RetrofitInstance.api.getEventById(eventId)
        if (response.success) {
            return response.event
        } else {
            // Handle the error or return an empty list
            return Events()
        }
    }

    //intestedEvent function
    suspend fun interestedEvent(eventId: String): String {
        val response = RetrofitInstance.api.interestedEvent(eventId)
        if (response.success) {
            return response.message
            Log.d("EventsRepository", "interestedEvent: ${response.message}")
        } else {
            Log.d("EventsRepository", "interestedEvent: ${response.message}")
            return ""
        }
    }
    //goingEvent function
    suspend fun goingEvent(eventId: String): String {
        val response = RetrofitInstance.api.goingEvent(eventId)
        if (response.success) {
            return response.message
            Log.d("EventsRepository", "goingEvent: ${response.message}")
        } else {
            Log.d("EventsRepository", "goingEvent: ${response.message}")
            return ""
        }
    }
    //deleteEvent function
    suspend fun deleteEvent(eventId: String): String {
        val response = RetrofitInstance.api.deleteEvent(eventId)
        if (response.success) {
            return response.message
            Log.d("EventsRepository", "deleteEvent: ${response.message}")
        } else {
            Log.d("EventsRepository", "deleteEvent: ${response.message}")
            return ""
        }
    }


}


    // Add more repository methods as needed
