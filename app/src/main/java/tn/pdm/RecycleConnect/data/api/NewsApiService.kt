package tn.pdm.RecycleConnect.data.api

import retrofit2.http.GET
import retrofit2.http.POST
import tn.pdm.RecycleConnect.data.models.News

interface NewsApiService {
    @GET("api/news")
    suspend fun getAllNews(): ApiResponseNews<List<News>>

    @POST("api/news/scrape-news")
    suspend fun scrapeNews(): ApiResponseNews<List<News>>

    @GET("api/news//schedule-news-scraping")
    suspend fun scheduleNewsScraping(): ApiResponseNewsSchedule<String>
}
data class ApiResponseNews<T>(
    val success: Boolean,
    val news: T
)
data class ApiResponseNewsSchedule<T>(
    val success: Boolean,
    val message: T
)


