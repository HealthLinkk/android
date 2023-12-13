package tn.pdm.RecycleConnect.data.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import tn.pdm.RecycleConnect.data.models.Events

interface EventsApiService {
    @GET("/api/events")
    suspend fun getAllEvents(): ApiResponse<List<Events>>

    @Multipart
    @POST("/api/events/create")
    suspend fun createEvent(
        @Part ("nameEvent") nameEvent: RequestBody,
        @Part ("descriptionEvent") descriptionEvent: RequestBody,
        @Part ("addressEvent") addressEvent: RequestBody,
        @Part ("startEvent") startEvent: RequestBody,
        @Part image: MultipartBody.Part
    ): ApiResponse<Events>

    @GET("/api/events/{id}")
    suspend fun getEventById(@Path("id") eventId: String): ApiResponseID<Events>

    @POST("/api/events/{id}/interested")
    suspend fun interestedEvent(@Path("id") eventId: String): ApiResponseIntrested_going


    @POST("/api/events/{id}/going")
    suspend fun goingEvent(@Path("id") eventId: String): ApiResponseIntrested_going

    @DELETE("/api/events/delete/{id}")
    suspend fun deleteEvent(@Path("id") eventId: String): ApiResponseDelete
}
data class ApiResponse<T>(
    val success: Boolean,
    val events: T
)

data class ApiResponseID<T>(
    val success: Boolean,
    val event: T
)
data class ApiResponseIntrested_going(
    val success: Boolean,
    val message: String
)
data class ApiResponseDelete(
    val success: Boolean,
    val message: String
)


