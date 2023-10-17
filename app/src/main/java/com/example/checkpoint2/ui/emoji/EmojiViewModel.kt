package com.example.checkpoint2.ui.emoji

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkpoint2.data.remote.GithubApiService
import com.example.checkpoint2.data.model.Emoji
import com.example.checkpoint2.data.remote.GithubApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import kotlinx.coroutines.launch
class EmojiViewModel() : ViewModel() {

    private val _emojiList = MutableLiveData<List<Emoji>>()
    val emojiList: LiveData<List<Emoji>> get() = _emojiList


    fun searchEmojis() {
        viewModelScope.launch {
            try {
                // chamada à API com o Retrofit
                val response = GithubApi.retrofitService.getEmojis()
                val emojis = response.map { Emoji(name = it.key, url = it.value) } // Mapeia para List<Emoji>
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

    fun showRandomEmoji(imageView: ImageView) {
        getRandomEmoji()?.let { emoji ->
            // Agora, carregamos diretamente a imagem na ImageView fornecida usando Coil
            imageView?.let {
                it.load(emoji.url) {
                    crossfade(true)

                }
            } ?: run {
                Log.e("RandomEmoji", "ImageView not set.")
            }
        } ?: run {
            Log.e("RandomEmoji", "No emojis available.")
        }
    }
}
