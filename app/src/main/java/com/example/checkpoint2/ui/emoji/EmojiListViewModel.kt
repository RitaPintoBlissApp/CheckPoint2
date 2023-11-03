package com.example.checkpoint2.ui.emoji

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkpoint2.data.model.Emoji
import com.example.checkpoint2.data.remote.EmojiApi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch


class EmojiListViewModel() : ViewModel() {

    private val _emojiList = MutableLiveData<List<Emoji>?>() //lista de emojis
    val emojiList: MutableLiveData<List<Emoji>?> get() = _emojiList

    /*
         fun getEmojis() {
            viewModelScope.launch {
                try {
                    // chamada à API com o Retrofit
                    val listResult = EmojiApi.retrofitService.getEmojis()
                    val emojis = listResult.map { Emoji(name = it.key, imgSrc = it.value) } // Mapeia para List<Emoji>
                    _emojiList.postValue(emojis)  // Atualizar UI

                } catch (e: Exception) {
                    Log.e("APIError", e.toString())// Lida com os erros
                }
            }
        }*/

    //Desafio 2/11
    //vai buscar os dados guardados
    //de houver dados na cache top
    //se não houver vais buscar á API

    //Alternativas ao Gson
    fun getEmojisFromCacheOrAPI(context: Context) {
        val cachedEmojis = getCahedEmojis(context)
        Log.v("TAG", "cachedEmojis = $cachedEmojis")
        if (cachedEmojis != null) {
            _emojiList.value = cachedEmojis
        } else {
              getEmojisFromApi(context)
        }
    }

    fun getCahedEmojis(context: Context): Emoji? {
        val sharedPreferences = context.getSharedPreferences("emoji_cache", Context.MODE_PRIVATE)
        val emojisSaved = sharedPreferences.getString("emojis", null)
        Log.v("TAG", "emojis saved = $emojisSaved")

        return emojisSaved?.let {//se emojisSaved n for nulo
            //vamos "converter" JSON para uma lista de emojis com o Gson
            //val emojis = Gson().fromJson(it, Array<Emoji>::class.java).toList()
            //emojis

            val moshi: Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<Emoji> = moshi.adapter<Emoji>(
                Emoji::class.java
            )

            val blackjackHand = jsonAdapter.fromJson(it)
            println(blackjackHand)
            blackjackHand

            //desserialização
        }
    }


    private fun saveEmojisToCache(context: Context, emoji: Emoji) {
        val sharedPreferences = context.getSharedPreferences("emoji_cache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Emoji> = moshi.adapter<Emoji>(
            Emoji::class.java
        )


        val emojiJson = jsonAdapter.toJson() //converte para json
        Log.v("TAG", "emoji saved = $emojiJson")

        editor.putString(
            "emojis",
            emojiJson
        ) //guarda string JSON no SharedPreferences com a chave "emojis".
        editor.apply()
        //serialização
    }


    fun getEmojisFromApi(context: Context) {
        viewModelScope.launch {
            try {
                // Chamar a API para obter os emojis
                val apiResult = EmojiApi.retrofitService.getEmojis()
                val emojis = apiResult.map {
                    Emoji(
                        name = it.key,
                        imgSrc = it.value
                    )
                } //mapeia os dados da api para obetos emojis
                _emojiList.postValue(emojis)

                // Salvar os emojis no cache
                saveEmojisToCache(context, emojis)
            } catch (e: Exception) {

                Log.e("APIError", e.toString())
            }
        }
    }


    fun removeEmoji(position: Int) {
        _emojiList.value?.toMutableList()?.let { emojis ->
            emojis.removeAt(position)
            _emojiList.postValue(emojis)
        }
    }


}
