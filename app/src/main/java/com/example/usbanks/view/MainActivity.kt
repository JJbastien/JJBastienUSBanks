package com.example.usbanks.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.usbanks.R
import com.example.usbanks.util.NetworkResult
import com.example.usbanks.util.NewsAdapter
import com.example.usbanks.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        newsAdapter = NewsAdapter()
        fetchData()
    }

    private fun fetchData() {
        newsViewModel.fetchNewsResponse()
        newsViewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { newsAdapter.getTitle(it.articles) }
                    recyclerView.adapter = newsAdapter
                }
                is NetworkResult.Error -> {
                    Toast.makeText(this, "Error in fetching data", Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Loading -> {
                    // show a progress bar
                }
            }
        }
    }
}
