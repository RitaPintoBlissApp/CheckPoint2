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
    private val _emoji = MutableLiveData<Emoji?>()
    val emoji: LiveData<Emoji?> get() = _emoji


  private val _avatar = MutableLiveData<Avatar?>()
    val avatar: LiveData<Avatar?> get() = _avatar



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


    fun searchAvatar(nome: String, callback:(String)-> Unit) {
        viewModelScope.launch {
            try {
                val avatarResult = AvatarApi.retrofitAvatarService.searchAvatar(nome) // chamada à API, através do retrofit
                _avatar.value = avatarResult //atualiza
            } catch (e: Exception) {
                Log.v("TAG", "Erro na API ")
                callback("Erro na Api")
                //showMessage()
                //_avatar.postValue() //avatar default

            }
        }
    }

    fun initializeImageView(emoji: String?, avatar: String?) {
        if (avatar != null && emoji == null) {
            val avatarList = listOf(Avatar(name = "", id = 0.0, avatarSrc = avatar))
            _avatar.postValue(avatarList.first())
            _emoji.postValue(null)

        } else if (emoji != null && avatar == null) {
            _emojiList.value?.let { emojis ->
                if (emojis.isNotEmpty()) {
                    val randomIndex = (0 until emojis.size).random()
                    _emoji.postValue(emojis[randomIndex])
                }
            }
            _avatar.postValue(null)
        }
    }

}




