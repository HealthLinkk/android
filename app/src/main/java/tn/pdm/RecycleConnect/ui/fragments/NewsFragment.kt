package tn.pdm.RecycleConnect.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tn.pdm.RecycleConnect.data.repositories.NewsRepository
import tn.pdm.RecycleConnect.data.viewmodels.NewsViewModel
import tn.pdm.RecycleConnect.data.viewmodels.NewsViewModelFactory
import tn.pdm.RecycleConnect.databinding.FragmentNewsBinding
import tn.pdm.RecycleConnect.ui.adapters.NewsAdapter

const val TAG_news = "NewsFragment"
class NewsFragment : Fragment() {
   lateinit var binding: FragmentNewsBinding
   //oncreateview
    private lateinit var viewModel: NewsViewModel
    private val repository = NewsRepository()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater)
        // instantiate the EventsViewModel
        viewModel = ViewModelProvider(this, NewsViewModelFactory(repository))
            .get(NewsViewModel::class.java)
        val adapter = NewsAdapter(listOf())
        binding.rvNews.adapter = adapter
        // Observe the LiveData from the ViewModel
        viewModel.news.observe(viewLifecycleOwner, Observer { news ->
            Log.d(TAG_news, "onChanged: Events size - ${news.size}")
            adapter.newsList = news
            adapter.notifyDataSetChanged()
        })

        viewModel.getAllNews()

        binding.rvNews.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
}