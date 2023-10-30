package com.example.checkpoint2.ui.avatar

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkpoint2.data.model.Avatar
import com.example.checkpoint2.data.remote.AvatarApi
import kotlinx.coroutines.launch

class AvatarListViewModel : ViewModel() {


    private val _avatarList = MutableLiveData<List<Avatar>>()
    val avatarList: LiveData<List<Avatar>> get() = _avatarList
    private val PREFS_FILEAVATAR = "avatarprefs"

    fun getAvatarFromPrefs(context: Context) {
        Log.d("TAG", "getAvatarFromPrefs foi chamado")
        val prefs = context.getSharedPreferences(PREFS_FILEAVATAR, Context.MODE_PRIVATE)
        val savedURL = prefs.getString("avatarUrl", null)

        if (savedURL != null) {
            Log.d("TAG", "URL : $savedURL")

            // Se a URL estiver salva nas SharedPreferences, cria um Avatar com essa URL
            val avatar =  Avatar(name = "", id = 0.0, avatarSrc = savedURL)

            val currentList = _avatarList.value?.toMutableList() ?: mutableListOf()
            currentList.add(avatar)
            _avatarList.postValue(currentList)


        } else {
            Log.d("TAG", "No URL found")
            // Se a URL não estiver salva, posta uma lista vazia
            _avatarList.postValue(emptyList())
        }
    }

    fun removeAvatar(position:Int){
        _avatarList.value?.toMutableList()?.let { avatars ->
            avatars.removeAt(position) // remove o avatar da lista 'avatars'
            _avatarList.postValue(avatars) // atualiza a livedata
        }
    }

    fun getValue( mapaV: List<Map< String, Any>>): List<Avatar>{
        var user=""
        var id: Double = 0.0
        var url = ""
        var listaauxiliar = mutableListOf<Avatar>()
        mapaV.forEach(){
            it.forEach(){
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