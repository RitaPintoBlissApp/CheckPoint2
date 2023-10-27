package com.example.checkpoint2.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkpoint2.data.model.Avatar
import com.example.checkpoint2.data.model.Emoji
import com.example.checkpoint2.data.remote.AvatarApi
import com.example.checkpoint2.data.remote.EmojiApi
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _emojiList = MutableLiveData<List<Emoji>>()
    private val _emoji = MutableLiveData<Emoji>()
    val emoji: LiveData<Emoji> get() = _emoji


    private val _avatarSearchResult = MutableLiveData<List<Avatar>>()

    val avatarSearchResult: MutableLiveData<List<Avatar>> get() = _avatarSearchResult


    fun getEmojis() {
        viewModelScope.launch {
            try {
                // chamada à API com o Retrofit
                val listResult = EmojiApi.retrofitService.getEmojis()
                val emoji = listResult.map {
                    Emoji(
                        name = it.key,
                        imgSrc = it.value
                    )
                } // Mapeia para List<Emoji>
                _emojiList.postValue(emoji)  // Atualizar UI
                _emoji.postValue(emoji.random())

            } catch (e: Exception) {
                Log.e("TAG", e.toString())// Lida com os erros
            }
        }
    }

    fun getEmoji() {
        _emojiList.value?.let { emojis ->
            if (emojis.isNotEmpty()) {
                val randomIndex = (0 until emojis.size).random()
                _emoji.postValue(emojis[randomIndex])
            }
        }
    }


    fun seartchAvatar(nome: String) {
        viewModelScope.launch {
            try {
                val listResult = AvatarApi.retrofitAvatarService.searchAvatar(nome) // chamada à API, através do retrofit
                val avatars = getValue(listResult)
                _avatarSearchResult.postValue(avatars) //atualiza
            } catch (e: Exception) {
                Log.e("APIError", e.toString())
                _avatarSearchResult.postValue(emptyList())
            }
        }
    }

    fun getValue(mapaV: List<Map<String, Any>>): List<Avatar> {
        var user = ""
        var id: Double = 0.0
        var url = ""
        var listaauxiliar = mutableListOf<Avatar>()
        mapaV.forEach() {
            it.forEach() {
                when (it.key) {
                    "name" -> {
                        user = it.value as String
                    }

                    "id" -> {
                        id = it.value as Double
                    }

                    "avatar_url" -> {
                        url = it.value as String
                    }
                }
            }
            listaauxiliar.add(Avatar(name = user, id = id, avatarSrc = url))
        }
        return listaauxiliar
    }

}




