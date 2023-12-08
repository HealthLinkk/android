package com.esprit.healthlink.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esprit.healthlink.data.model.Appointment
import com.esprit.healthlink.R
import com.esprit.healthlink.databinding.ListAppointmentsBinding


class AppointmentAdapter: RecyclerView.Adapter<AppointmentAdapter.AppointmentsHolder>() {
    var items = ArrayList<Appointment>()

    fun setData(data : ArrayList<Appointment>){
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListAppointmentsBinding.inflate(layoutInflater)
        return AppointmentsHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: AppointmentsHolder, position: Int) {
        holder.bind(items[position])
    }

    class AppointmentsHolder(val binding: ListAppointmentsBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(data: Appointment){
            binding.appointmentsData = data
            binding.executePendingBindings()
        }

    }

}