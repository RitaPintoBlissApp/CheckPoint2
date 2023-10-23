package com.example.checkpoint2.ui.repo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.checkpoint2.R
import com.example.checkpoint2.databinding.ActivityAvatarListBinding
import com.example.checkpoint2.databinding.ActivityGoogleRepoBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class RepoListActivity() : AppCompatActivity(){

    private lateinit var binding: ActivityGoogleRepoBinding
    private val viewModel: RepoListViewModel  by viewModels()
    private lateinit var mAdapter: GoogleRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoogleRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()
        loadingData()
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