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

    private val _emojiListList = MutableLiveData<List<Emoji>>()
    val emojiListList : LiveData<List<Emoji>> get() = _emojiListList

    private val _emoji = MutableLiveData<Emoji>()
    val emoji : LiveData<Emoji> get() = _emoji
    fun getEmojis() {
        viewModelScope.launch {
            try {
                // chamada à API com o Retrofit
                val listResult = EmojiApi.retrofitService.getEmojis()
                val emoji = listResult.map { Emoji(name = it.key, url = it.value) } // Mapeia para List<Emoji>
                _emojiListList.postValue(emoji)  // Atualizar UI
                _emoji.postValue(emoji.random())
            } catch (e: Exception) {
                Log.e("APIError", e.toString())// Lida com os erros
            }
        }
    }


}