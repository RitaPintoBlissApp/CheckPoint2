package com.example.checkpoint2.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkpoint2.data.model.GoogleRepo
import com.example.checkpoint2.data.remote.GoogleRApi
import kotlinx.coroutines.launch

class RepoListViewModel(): ViewModel() {

    private val _googleRepoList = MutableLiveData<List<GoogleRepo>>()
    val googleRepoList: LiveData<List<GoogleRepo>> get() = _googleRepoList

    fun getGoogleRepo(){
        viewModelScope.launch {
            try{
                val listResult = GoogleRApi.GoogleRepoService.getGoogleRepo()  // chamada à API, através do retrofit, para retirar os dados pretendidos
                val repos = listResult.map { GoogleRepo( id = it.id,  fullName = it.fullName, private = it.private) } //mapeia os dados para uma lista
                _googleRepoList.postValue(repos) //atualiza a mutablelive data

            }catch (e:Exception){
                Log.e("APIError", e.toString()) //avisa que dá erro
                _googleRepoList.postValue(emptyList()) // limpa a mutable live data list
            }
        }
    }
}




/*
 val listData = Pager(PagingConfig(pageSize = 1)){
        GoogleRepoSource(apiservice)
    }.flow.cachedIn(viewModelScope)
    */
