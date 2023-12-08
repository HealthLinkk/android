package com.esprit.healthlink.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esprit.healthlink.data.adapter.AppointmentAdapter

import com.esprit.healthlink.data.model.Appointment
import com.esprit.healthlink.data.model.AppointmentRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppointmentsViewModel(private val repository: AppointmentRepository) : ViewModel() {
    val appointmentsData = MutableLiveData<List<Appointment>>()
    val appointmentsLiveData: LiveData<List<Appointment>> = appointmentsData

    var dataAdapter: AppointmentAdapter = AppointmentAdapter()

    init {
        makeApiCall()
    }
    fun getAdapter(): AppointmentAdapter {
        return dataAdapter
    }

    fun setAdapterData(data : ArrayList<Appointment>){
        dataAdapter.setData(data)
        dataAdapter.notifyDataSetChanged()
    }


    fun makeApiCall() {
        repository.getScheduledRendezVous().enqueue(object : Callback<List<Appointment>> {
            override fun onFailure(call: Call<List<Appointment>>, t: Throwable) {
                Log.e("AppointmentsViewModel", "API call failed", t)

            }

            override fun onResponse(call: Call<List<Appointment>>, response: Response<List<Appointment>>) {
                Log.d("AppointmentsViewModel", "API call successful")
                if (response.isSuccessful) {

                    appointmentsData.value = response.body()
                } else {
                    Log.e("AppointmentsViewModel", "API call not successful. Code: ${response.code()}")
                }
            }


        })
    }

}