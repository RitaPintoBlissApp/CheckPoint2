package com.example.checkpoint2.ui.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.checkpoint2.R
import com.example.checkpoint2.databinding.ActivityAvatarListBinding
import com.example.checkpoint2.databinding.ActivityGoogleRepoBinding

class RepoListActivity() : AppCompatActivity(){

    private lateinit var binding: ActivityGoogleRepoBinding
    private val viewModel: RepoListViewModel = RepoListViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = GoogleRepoAdapter {}

        binding = ActivityGoogleRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGoogleRepos.layoutManager = GridLayoutManager(this,4)

        binding.rvGoogleRepos.adapter = adapter

        viewModel.googleRepoList.observe(this){list ->
            //adapter.updateItems(list)
        }

        viewModel.getGoogleRepo()
    }
}