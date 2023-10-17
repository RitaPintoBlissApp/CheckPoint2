package com.example.checkpoint2.ui.emoji

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.checkpoint2.data.remote.EmojiApiService

class EmojiViewModelFactory( private val apiService: EmojiApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmojiListViewModel::class.java)) {
            return EmojiListViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
