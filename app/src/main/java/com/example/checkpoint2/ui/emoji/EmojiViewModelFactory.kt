package com.example.checkpoint2.ui.emoji

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.checkpoint2.data.remote.GithubApiService

class EmojiViewModelFactory( private val apiService: GithubApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmojiViewModel::class.java)) {
            return EmojiViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
