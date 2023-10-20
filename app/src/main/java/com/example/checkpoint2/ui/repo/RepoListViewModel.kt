package com.example.checkpoint2.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkpoint2.data.model.Avatar
import com.example.checkpoint2.data.model.GoogleRepo
import com.example.checkpoint2.data.remote.AvatarApi
import com.example.checkpoint2.data.remote.GoogleRApi
import kotlinx.coroutines.launch

class RepoListViewModel: ViewModel() {

    private val _googleRepoList = MutableLiveData<List<GoogleRepo>>()
    val googleRepoList: LiveData<List<GoogleRepo>>get() = _googleRepoList

    fun getGoogleRepo(){
        viewModelScope.launch {
            try{
                val listResult = GoogleRApi.retrofitGoogleRepoService.getGoogleRepo() // chamada à API, através do retrofit
                // val repos = listResult.map { GoogleRepo( id = it.key,full_name = it.value, private = it.value) }
                //_googleRepoList.postValue(repos) //atualiza
            }catch (e:Exception){
                Log.e("APIError", e.toString())
                _googleRepoList.postValue(emptyList())
            }
        }
    }

}