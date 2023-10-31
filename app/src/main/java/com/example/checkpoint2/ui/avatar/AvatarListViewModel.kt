package com.example.checkpoint2.ui.avatar

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.checkpoint2.data.model.Avatar

class AvatarListViewModel : ViewModel() {


    private val _avatarList = MutableLiveData<List<Avatar>?>()
    val avatarList: MutableLiveData<List<Avatar>?> get() = _avatarList
    private val prefsFileAvatar_ = "avatarprefs"

    fun getAvatarFromPrefs(context: Context) {
        Log.d("TAG", "getAvatarFromPrefs foi chamado")
        val prefs = context.getSharedPreferences(prefsFileAvatar_, Context.MODE_PRIVATE)
        val savedList = prefs.getString("avatarUrls", null)?.trim(',')


        if (savedList != null) {
            Log.d("TAG", "Lista de URLs: $savedList")
            // Cria uma lista de dos URLs
            val avatarUrls = savedList.split(",").toList()

            // Cria uma lista de avatares a partir dos URLs
            val avatarList = avatarUrls.map { Avatar(name = "", id = 0.0, avatarSrc = it) }

            // Atualiza a lista no ViewModel
            _avatarList.postValue(avatarList)


        } else {
            Log.d("TAG", "No URL found")
            // Se a URL não estiver salva, posta uma lista vazia
            _avatarList.postValue(emptyList())
        }
    }




    fun removeAvatar(position: Int, context: Context) {
        val avatarList = _avatarList.value?.toMutableList()

        if (avatarList != null && position >= 0 && position < avatarList.size) {
            avatarList.removeAt(position)
            _avatarList.postValue(avatarList)

            val avatarUrls = avatarList.map { it.avatarSrc }
            saveAvatarUrlsToPrefs(avatarUrls, context)
        }
    }

    private fun saveAvatarUrlsToPrefs(avatarUrls: List<String>, context: Context) {
        val sharedPreferences = context.getSharedPreferences(prefsFileAvatar_, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit() //Cria um editor para realizar as modificações nas preferências compartilhadas
        val serializedList = avatarUrls.joinToString(separator = ",")
        editor.putString("avatarUrls", serializedList.trim(','))
        editor.apply()
    }
    fun getValue( mapaV: List<Map< String, Any>>): List<Avatar>{
        var user=""
        var id: Double = 0.0
        var url = ""
        val listaauxiliar = mutableListOf<Avatar>()
        mapaV.forEach(){ it ->
            it.forEach(){ it ->
                when (it.key) {
                    "name" -> {user = it.value as String}
                    "id" -> {id = it.value as Double}
                    "avatar_url" -> {url = it.value as String}
                }
            }
           listaauxiliar.add( Avatar(name = user, id=id, avatarSrc = url ))
        }
    return listaauxiliar
    }
}