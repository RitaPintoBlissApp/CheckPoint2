package com.example.checkpoint2.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EmojiViewModel(private val apiService: GithubApiService) : ViewModel() {


    private val _emojiList = MutableLiveData<List<Emoji>>()
    val emojiList: LiveData<List<Emoji>> get() = _emojiList

     fun SearchEmojis() {
        val viewModelScope = null
        viewModelScope.launch {
            try {
                // Fazer a chamada à API com o Retrofit
                val response = apiService.getEmojis()
                val emojis = response.map { Emoji(name = it.key, url = it.value) } // Mapeia para List<Emoji>
                _emojiList.postValue(emojis)  // Atualizar UI ou fazer log
            } catch (e: Exception) {
                Log.e("APIError", e.toString())// Lidar com erros
            }
        }
    }

}