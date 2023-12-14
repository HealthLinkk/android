// CommentAdapter.kt
package com.esprit.healthlink.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esprit.healthlink.data.model.Comment
import com.esprit.healthlink.databinding.ActivityCommentBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    var items = ArrayList<Comment>()

    fun setData(data: List<Comment>) {
        this.items = ArrayList(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityCommentBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(private val binding: ActivityCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.commentData = comment
            binding.executePendingBindings()
        }
    }
}
