package com.example.checkpoint2.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkpoint2.data.model.Emoji
import com.example.checkpoint2.data.remote.EmojiApi
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel(){

    private val _emojiList = MutableLiveData<List<Emoji>>()
    val emojiList : LiveData<List<Emoji>> get() = _emojiList

    private val _emoji = MutableLiveData<Emoji>()
    val emoji : LiveData<Emoji> get() = _emoji


    fun getEmojis() {
        viewModelScope.launch {
            try {
                // chamada à API com o Retrofit
                val listResult = EmojiApi.retrofitService.getEmojis()
                val emoji = listResult.map { Emoji(name = it.key, imgSrc = it.value) } // Mapeia para List<Emoji>
                _emojiList.postValue(emoji)  // Atualizar UI
                _emoji.postValue(emoji.random())

            } catch (e: Exception) {
                Log.e("APIError", e.toString())// Lida com os erros
            }
        }
    }

    fun getEmoji(){
         _emojiList.value?.let { emojis ->
            if (emojis.isNotEmpty()) {
                val randomIndex = (0 until emojis.size).random()
                _emoji.postValue(emojis[randomIndex])
            }
        }
    }
}