package com.esprit.healthlink.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esprit.healthlink.data.adapter.DoctorAdapter
import com.esprit.healthlink.data.adapter.PostAdapter
import com.esprit.healthlink.data.model.Doctor
import com.esprit.healthlink.data.model.Message
import com.esprit.healthlink.data.model.PatientSignupRequest
import com.esprit.healthlink.data.model.Post
import com.esprit.healthlink.data.model.PostRepository
import com.esprit.healthlink.data.model.PostRequest
import com.esprit.healthlink.data.model.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel(private val repository: PostRepository) : ViewModel() {
    var successful: Boolean = false
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts
    var dataAdapter: PostAdapter = PostAdapter()
    fun AddPostApiCall(
        title: String,
        content: String

    ) {
val author = "6557c2113e63a3bf823316e3"
        val addPostRequest = PostRequest(
            title = title,
            content = content,
            author = author

        )

        repository.AddPost(addPostRequest)
            .enqueue(object : Callback<Message> {
                override fun onResponse(
                    call: Call<Message>,
                    response: Response<Message>
                ) {
                    if (response.isSuccessful) {
                        // Handle the successful response
                        val responseBody = response.body()
                        successful = (response.code() == 200)
                        Log.e(
                            "PostViewModel",
                            "  success. Code: ${responseBody}"
                        ) // Process the responseBody
                    } else {
                        Log.e("PostViewModel", "  failed. error: ${response.code()}")

                    }
                }

                override fun onFailure(call: Call<Message>, t: Throwable) {
                    // Handle failure
                    Log.e("PostViewModel", " Add Post API call failed", t)
                }
            })

    }
    fun getPosts() {
        repository.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    _posts.value = response.body()
                } else {
                    // Gérer l'erreur de réponse
                    Log.e("PostViewModel", "Failed to get posts. Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                // Gérer l'échec de l'appel
                Log.e("PostViewModel", "Get Posts API call failed", t)
            }
        })
    }
    init {
        makeApiCall()
    }

    fun getAdapter(): PostAdapter {
        return dataAdapter
    }

    fun setAdapterData(data : ArrayList<Post>){
        dataAdapter.setData(data)
        dataAdapter.notifyDataSetChanged()
    }

    fun makeApiCall() {
        repository.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("PostViewModel", "API call failed", t)
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                Log.d("PostViewModel", "API call successful")
                if (response.isSuccessful) {
                    _posts.value = response.body()
                } else {
                    Log.e("PostViewModel", "API call not successful. Code: ${response.code()}")
                }
            }
        })
    }

}