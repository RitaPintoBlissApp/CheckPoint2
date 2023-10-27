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

    private var currentPage = 1
    private var currentLimit = 30
    private var updating = false

    private val _googleRepoList = MutableLiveData<List<GoogleRepo>>()
    val googleRepoList: LiveData<List<GoogleRepo>> get() = _googleRepoList

    fun startUpdate(){
        updating = true
    }

    fun finishUpdate(){
        updating = false
    }

    fun isUpdating(): Boolean{
        return updating
    }

    fun getGoogleRepo(){
        viewModelScope.launch {
            try{
                val listResult = GoogleRApi.GoogleRepoService.getGoogleRepo(limit = currentLimit, page = currentPage )  // chamada à API, através do retrofit, para retirar os dados pretendidos
                val repos = listResult.map { GoogleRepo( id = it.id,  fullName = it.fullName, private = it.private) } //mapeia os dados para um objeto GoogleRepo
                _googleRepoList.postValue(repos) //e atualiza a lista

            }catch (e:Exception){
                Log.e("APIError", e.toString()) //avisa que dá erro
                _googleRepoList.postValue(emptyList()) // limpa a mutable live data list
            }
        }
    }


    fun getNextGoogleRepos(){

        if (updating ) {
            Log.v("TAG", "is updating")
        }
        else {
           startUpdate()
            viewModelScope.launch {
                try{
                    currentPage++ //incrementa a pagina
                    val listResult = GoogleRApi.GoogleRepoService.getGoogleRepo(limit = currentLimit, page = currentPage ) // chama a API
                    val repos = listResult.map { GoogleRepo(id = it.id, fullName = it.fullName, private = it.private) }
                    val currentList = _googleRepoList.value.orEmpty()
                    _googleRepoList.value = currentList + repos // Atualiza a lista atual com os novos repositórios
                    finishUpdate()
                }catch (e:Exception){
                    Log.v("TAG", "Exception")
                    finishUpdate()

                }

            }

        }

    }
}




/*
 val listData = Pager(PagingConfig(pageSize = 1)){
        GoogleRepoSource(apiservice)
    }.flow.cachedIn(viewModelScope)
    */
