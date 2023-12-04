package com.esprit.healthlink.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esprit.healthlink.data.adapter.DoctorAdapter
import com.esprit.healthlink.data.model.Doctor
import com.esprit.healthlink.data.model.Doctors
import com.esprit.healthlink.data.model.UserRepository
import com.esprit.healthlink.network.RetrofitClient
import com.esprit.healthlink.network.UserApiEndPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoctorsViewModel(private val repository: UserRepository) : ViewModel() {
     val doctorsData = MutableLiveData<List<Doctor>>()
    val doctorsLiveData: LiveData<List<Doctor>> = doctorsData

    var dataAdapter: DoctorAdapter = DoctorAdapter()

    init {
        makeApiCall()
    }
    fun getAdapter(): DoctorAdapter {
        return dataAdapter
    }

    fun setAdapterData(data : ArrayList<Doctor>){
        dataAdapter.setData(data)
        dataAdapter.notifyDataSetChanged()
    }


    fun makeApiCall() {
        repository.getDoctors().enqueue(object : Callback<List<Doctor>> {
            override fun onFailure(call: Call<List<Doctor>>, t: Throwable) {
                Log.e("DoctorsViewModel", "API call failed", t)

            }

            override fun onResponse(call: Call<List<Doctor>>, response: Response<List<Doctor>>) {
                Log.d("DoctorsViewModel", "API call successful")
                if (response.isSuccessful) {

                    doctorsData.value = response.body()
                } else {
                    Log.e("DoctorsViewModel", "API call not successful. Code: ${response.code()}")
                }
            }
        })
    }

}