package tn.pdm.RecycleConnect.data.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tn.pdm.RecycleConnect.data.models.News
import tn.pdm.RecycleConnect.data.repositories.NewsRepository

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    // LiveData for News
    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news

    // Function to fetch events
    fun getAllNews() {
        viewModelScope.launch {
            try {
                val newsList = repository.getAllNews().sortedByDescending { it.date }
                Log.e("NewsViewModel", "getAllNews: $news.size")
                _news.postValue(newsList)
            } catch (e: Exception) {
                Log.e("NewsViewModel", "getAllNews: ${e.message}")
                // Handle the exception
            }
        }
    }
    // Add a function to sechedule news scraping
    fun scheduleNewsScraping() {
        viewModelScope.launch {
            try {
                val message = repository.scheduleNewsScraping()
                // Handle the success or failure, maybe update UI or show a toast
                Log.d("NewsViewModel", "scheduleNewsScraping: $message")
            } catch (e: Exception) {
                Log.e("NewsViewModel", "scheduleNewsScraping: ${e.message}")
                // Handle the exception
            }
        }
    }



}
