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
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch

//import kotlinx.serialization.json.Json
//import kotlinx.serialization.*


class EmojiListViewModel() : ViewModel() {

    private val _emojiList = MutableLiveData<List<Emoji>?>() //lista de emojis
    val emojiList: MutableLiveData<List<Emoji>?> get() = _emojiList





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
        }

    //TODO
    //vai buscar os dados guardados
    //de houver dados na cache top
    //se não houver vais buscar á API
/*
    //Alternativas ao Gson
    fun getEmojisFromCacheOrAPI(context: Context) {
        val cachedEmojis = getCachedEmojis(context)
        Log.v("TAG", "cachedEmojis = $cachedEmojis")
        if (cachedEmojis != null) {
            _emojiList.value = cachedEmojis
        } else {
            getEmojisFromApi(context)
        }
    }

    fun getCachedEmojis(context: Context): List<Emoji>? {
        val sharedPreferences = context.getSharedPreferences("emoji_cache", Context.MODE_PRIVATE)
        val emojisSaved = sharedPreferences.getString("emojis", null)
        Log.v("TAG", "emojis saved = $emojisSaved")

        return emojisSaved?.let {//.let -> executa apenas se emojisSaved n for nulo
            //vamos "converter" JSON para uma lista de emojis com o Gson
            //val emojis = Gson().fromJson(it, Array<Emoji>::class.java).toList()
            //emojis

            val moshi: Moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            Log.v("TAG", "1")

            //criamos um adaptador JSON específico para a classe Emoji, que indica ao Moshi como converter objetos dessa classe para JSON e vice-versa
            val listjsonAdapter: JsonAdapter<List<Emoji>> = moshi.adapter(Types.newParameterizedType(List::class.java, Emoji::class.java).rawType)
            Log.v("TAG", "$listjsonAdapter")

            // usamos o adaptador para desserializar a string JSON (it) em um objeto Emoji.
            val emoji = listjsonAdapter.fromJson(it)
            Log.v("TAG", "3")

            // imprimimos o objeto desserializado.
            println(emoji)
            Log.v("TAG", "4")

            //retorna o objeto Emoji desserializado
            emoji

        }
    }


    private fun saveEmojisToCache(context: Context, emojis: List<Emoji>) {
        // Obtém a referência para o SharedPreferences com o nome "emoji_cache"
        val sharedPreferences = context.getSharedPreferences("emoji_cache", Context.MODE_PRIVATE)

        // editor para modificar o SharedPreferences
        val editor = sharedPreferences.edit()

        // Converte o objeto Emoji para uma string JSON
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<List<Emoji>> = moshi.adapter<List<Emoji>>(
            Types.newParameterizedType(List::class.java,  Emoji::class.java))

        val emojisJson = jsonAdapter.toJson(emojis) ?: "[]" //converte para json ou usa uma lista vazia se a conversão falhar
        Log.v("TAG", "emoji passed to Json = $emojisJson")

        //guarda string JSON no SharedPreferences com a chave "emojis".
        editor.putString("emojis", emojisJson)
        editor.apply()
        //serialização
    }
    /*
    *   Types.newParameterizedType(//cria um tipo de parâmetro usando as classes fornecidas.
        List::class.java, //Obtém a representação da classe List em Java. List é uma interface genérica, mas aqui é usada sem parâmetros de tipo, para representar uma lista sem tipo específico.
        Emoji::class.java) //Obtém a representação da classe Emoji em Java*/


    fun getEmojisFromApi(context: Context) {
        viewModelScope.launch {
            try {
                // Chamar a API para obter os emojis
                val apiResult = EmojiApi.retrofitService.getEmojis()
                val emojis = apiResult.map { Emoji(name = it.key, imgSrc = it.value) } //mapeia os dados da api para obetos emojis
                _emojiList.postValue(emojis) // atualiza a LiveData

                // Salvar os emojis no cache
                saveEmojisToCache(context, emojis)

            } catch (e: Exception) {
                Log.v("TAG", e.toString())
            }
        }
    }*/


    fun removeEmoji(position: Int) {
        _emojiList.value?.toMutableList()?.let { emojis ->
            emojis.removeAt(position)
            _emojiList.postValue(emojis)
        }
    }


}
