package tn.pdm.RecycleConnect.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tn.pdm.RecycleConnect.data.models.News
import tn.pdm.RecycleConnect.databinding.SingleItemNewsBinding

class NewsAdapter(var newsList: List<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleItemNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }


    class NewsViewHolder(private val binding: SingleItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.newsName.text = news.title
            binding.newsDescription.text = news.description
            binding.newsSource.text = news.source

            // Load the event image
            Glide.with(binding.root)
                .load(news.newsPhoto)
                .into(binding.newsImage)
            Log.e("NewsAdapter", "bind: ${news.newsPhoto}")

        }
    }
}
