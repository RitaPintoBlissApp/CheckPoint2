package com.example.checkpoint2.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.checkpoint2.data.model.Avatar
import com.example.checkpoint2.data.model.GoogleRepo
import com.example.checkpoint2.data.paging.GoogleRepoSource
import com.example.checkpoint2.data.remote.AvatarApi
import com.example.checkpoint2.data.remote.GoogleRApi
import com.example.checkpoint2.data.remote.ReposApiService
import kotlinx.coroutines.launch

class RepoListViewModel(
    private val apiservice: ReposApiService
): ViewModel() {

/*  private val _googleRepoList = MutableLiveData<List<GoogleRepo>>()
  val googleRepoList: LiveData<List<GoogleRepo>>get() = _googleRepoList

  fun getGoogleRepo(){
      viewModelScope.launch {
          try{
              val listsult = GoogleRApi.retrofitGoogleRepoService.getGoogleRepo() // chamada à API, através do retrofit, para retirar os dados pretendidos
              val repos = listResult.map { GoogleRepo( id = it.id,  fullName = it.fullName, private = it.private) } //mapeia os dados para uma lista
              _googleRepoList.postValue(repos) //atualiza a mutablelive data

          }catch (e:Exception){
              Log.e("APIError", e.toString()) //avisa que dá erro
              _googleRepoList.postValue(emptyList()) // limpa a mutable live data list
          }
      }
  }*/


    val listData = Pager(PagingConfig(pageSize = 1)){
        GoogleRepoSource(apiservice)
    }.flow.cachedIn(viewModelScope)
}