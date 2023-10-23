package com.example.checkpoint2.ui.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.checkpoint2.data.paging.GoogleRepoSource
import com.example.checkpoint2.data.remote.GoogleRApi

class RepoListViewModel(
    private val apiservice: GoogleRApi
): ViewModel() {
    val listData = Pager(PagingConfig(pageSize = 20)){
        GoogleRepoSource(apiservice)
    }.flow.cachedIn(viewModelScope)
}




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
