package com.example.checkpoint2.ui.avatar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkpoint2.R
import com.example.checkpoint2.data.model.Avatar
import com.example.checkpoint2.data.remote.AvatarApi
import kotlinx.coroutines.launch

class AvatarListViewModel : ViewModel() {

    private val _avatarList = MutableLiveData<List<Avatar>>()

    val avatarList:LiveData<List<Avatar>> get() = _avatarList //para leitura externa e representação dos avatares

    fun getAvatar(){
        viewModelScope.launch {
            try{
                val listResult = AvatarApi.retrofitAvatarService.getAvatar() // chamada à API, através do retrofit
               // val avatars = listResult.map { Avatar( name = it.name, id = it.id, avatarSrc = it.avatarSrc) }
                val avatars = getValue(listResult)
                _avatarList.postValue(avatars) //atualiza
            }catch (e:Exception){
                Log.e("APIError", e.toString())
                _avatarList.postValue(emptyList())
            }
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