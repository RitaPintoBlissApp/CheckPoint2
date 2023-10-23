package com.example.checkpoint2.ui.repo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.checkpoint2.data.remote.GoogleRApi
import com.example.checkpoint2.data.remote.ReposApiService

class RepoListViewModelFactory( private val apiservice: GoogleRApi): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GoogleRApi ::class.java)
            .newInstance(apiservice)
    }
}