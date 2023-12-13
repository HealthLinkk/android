package com.esprit.healthlink.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esprit.healthlink.data.model.Post

import com.esprit.healthlink.databinding.FragmentPostsBinding
import com.esprit.healthlink.databinding.ItemPostBinding

// PostAdapter.kt
class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    var items = ArrayList<Post>()

    fun setData(data: ArrayList<Post>) {
        this.items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.postData= post
            binding.executePendingBindings()
        }
    }
}


