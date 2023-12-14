package com.esprit.healthlink.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esprit.healthlink.data.model.Doctor
import com.esprit.healthlink.databinding.ListDoctorsBinding

class DoctorAdapter() : RecyclerView.Adapter<DoctorAdapter.DoctorsHolder>() {
    var items = ArrayList<Doctor>()

    fun setData(data : ArrayList<Doctor>){
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListDoctorsBinding.inflate(layoutInflater)
        return DoctorsHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: DoctorsHolder, position: Int) {
        holder.bind(items[position])

    }





    class DoctorsHolder(val binding: ListDoctorsBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(data: Doctor){
            binding.doctorData = data
            binding.executePendingBindings()
        }

    }


}