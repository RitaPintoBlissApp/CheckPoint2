package com.example.checkpoint2.ui.emoji

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkpoint2.data.remote.EmojiApiService
import com.example.checkpoint2.data.model.Emoji
import com.example.checkpoint2.data.remote.EmojiApi

import kotlinx.coroutines.launch
class EmojiListViewModel(apiService: EmojiApiService) : ViewModel() {

    private val _emojiList = MutableLiveData<List<Emoji>>()
    val emojiList: LiveData<List<Emoji>> get() = _emojiList


     fun getEmojis() {
        viewModelScope.launch {
            try {
                // chamada à API com o Retrofit
                val listResult = EmojiApi.retrofitService.getEmojis()
                val emojis = listResult.map { Emoji(name = it.key, url = it.value) } // Mapeia para List<Emoji>
                _emojiList.postValue(emojis)  // Atualizar UI
            } catch (e: Exception) {
                Log.e("APIError", e.toString())// Lidar com erros
            }
        }
    }

    fun getRandomEmoji(): Emoji? {
        return _emojiList.value?.let { emojis ->
            if (emojis.isNotEmpty()) {
                val randomIndex = (0 until emojis.size).random()
                return emojis[randomIndex]
            }
            return null
        }
    }



}
