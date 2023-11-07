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
import kotlin.math.log

class MainActivityViewModel : ViewModel() {

    // LiveData para o emoji e avatar
    /* LiveData conta com reconhecimento de ciclo de vida, ou seja,
     ele respeita o ciclo de vida de outros componentes do app*/

    private val _emojiList = MutableLiveData<List<Emoji>>()
    private val _emoji = MutableLiveData<Emoji?>()
    val emoji: LiveData<Emoji?> get() = _emoji


  private val _avatar = MutableLiveData<Avatar?>()
    val avatar: LiveData<Avatar?> get() = _avatar

    fun initializeImageView(urlEmoji: String?, urlAvatar: String?) {
        Log.v("TAG", "Inicio da inicialização")
        if (urlAvatar != null ) {
            _avatar.setValue(Avatar(name = "", id = 0.0, avatarSrc = urlAvatar))
            _emoji.setValue(null)

        } else if (urlEmoji!= null) {
            _emoji.setValue(Emoji(name = "", imgSrc = urlEmoji))
            _avatar.setValue(null)
        }else(Log.v("TAG", "Não há dados"))
    }

    fun getEmojis() {
        viewModelScope.launch {
            try {
                Log.v("TAG", "get emoji()?")
                // chamada à API com o Retrofit
                val listResult = EmojiApi.retrofitService.getEmojis()
                Log.v("TAG", "vai buscar os emojis API")
                val emoji = listResult.map {
                    Emoji(
                        name = it.key,
                        imgSrc = it.value
                    )
                } // Mapeia para List<Emoji>
                Log.v("TAG", "mapeia os emojis")
                _emojiList.setValue(emoji)  // Atualizar UI
               // _emoji.postValue(emoji.random())
                Log.v("TAG", "vai buscar os emojis")

            } catch (e: Exception) {
                Log.e("TAG", e.toString())// Lida com os erros
            }
        }
    }

    fun getEmoji() {
        _emojiList.value?.let { emojis ->
            if (emojis.isNotEmpty()) {
                val randomIndex = (0 until emojis.size).random()
                _emoji.value = emojis[randomIndex]
                Log.v("TAG", "vai buscar emojijs random")

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




}




