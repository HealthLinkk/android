package tn.pdm.RecycleConnect.data.repositories

import tn.pdm.RecycleConnect.data.api.RetrofitInstance
import tn.pdm.RecycleConnect.data.models.News

class NewsRepository {


    suspend fun getAllNews(): List<News> {
        // Make the API call
        val response = RetrofitInstance.apinews.getAllNews()
        if (response.success) {
            return response.news
        } else {
            return emptyList()
        }

    }
    suspend fun scheduleNewsScraping(): String {
        // Make the API call
        val response = RetrofitInstance.apinews.scheduleNewsScraping()
        if (response.success) {
            return response.message
        } else {
            return ""
        }

    }

    suspend fun scrapeNews(): List<News> {
        // Make the API call
        val response = RetrofitInstance.apinews.scrapeNews()
        if (response.success) {
            return response.news
        } else {
            return emptyList()
        }

    }
}