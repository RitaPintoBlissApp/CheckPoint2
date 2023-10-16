package com.example.checkpoint2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.checkpoint2.network.EmojiViewModel
import com.example.checkpoint2.network.GithubApiService

class EmojiViewModelFactory( private val apiService: GithubApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmojiViewModel::class.java)) {
            return EmojiViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
