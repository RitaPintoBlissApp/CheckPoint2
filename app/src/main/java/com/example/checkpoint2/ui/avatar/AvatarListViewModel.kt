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

    val avatarList:LiveData<List<Avatar>> get() = _avatarList

    fun getAvatar(){
        viewModelScope.launch {
            try{
                val listResult = AvatarApi.retrofitAvatarService.getAvatar()
               // val avatars = listResult.map { Avatar(login = it.key, id = it.key, avatarSrc = it.value) }
                val avatars = listResult.map { response ->
                    Avatar(
                        login = response.key,
                        id = response.id.toIntOrNull() ?: 0, // Converter para Int ou usar 0 em caso de falha na conversão
                        avatarSrc = response.value
                    )
                }
                _avatarList.postValue(avatars)
            }catch (e:Exception){
                Log.e("APIError", e.toString())
                _avatarList.postValue(emptyList())
            }
        }
    }

    fun removeAvatar(position:Int){
        _avatarList.value?.toMutableList()?.let { avatars ->
            avatars.removeAt(position)
            _avatarList.postValue(avatars)
        }
    }

}