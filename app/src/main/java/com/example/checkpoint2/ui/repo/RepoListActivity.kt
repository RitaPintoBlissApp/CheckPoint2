package com.example.checkpoint2.ui.repo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.checkpoint2.data.remote.GoogleRApi
import com.example.checkpoint2.data.remote.ReposApiService
import com.example.checkpoint2.databinding.ActivityGoogleRepoBinding
import com.example.checkpoint2.ui.repo.factory.RepoListViewModelFactory
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RepoListActivity() : AppCompatActivity(){

    private lateinit var binding: ActivityGoogleRepoBinding
    private val viewModel: RepoListViewModel  by viewModels(factoryProducer = {
        val repoListFactory = RepoListViewModelFactory(GoogleRApi())
        val repoList = ViewModelProvider(this, repoListFactory)
            .get(RepoListViewModel::class.java)
    repoListFactory
    })
    private lateinit var mAdapter: GoogleRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoogleRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()
        loadingData()

    }
    private fun loadingData(){
        lifecycleScope.launch {
            viewModel.listData.collect{ pagingData->
                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRV(){
        mAdapter = GoogleRepoAdapter()
        binding.rvGoogleRepos.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,StaggeredGridLayoutManager.VERTICAL
            )
            adapter=mAdapter
            setHasFixedSize(true)
        }
    }
}

annotation class AndroidEntryPoint


/*
        val adapter = GoogleRepoAdapter{}

        binding = ActivityGoogleRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGoogleRepos.layoutManager = GridLayoutManager(this,4)

        binding.rvGoogleRepos.adapter = adapter

        viewModel.googleRepoList.observe(this){list ->
            adapter.updateItem(list)
        }

        viewModel.getGoogleRepo()*/
