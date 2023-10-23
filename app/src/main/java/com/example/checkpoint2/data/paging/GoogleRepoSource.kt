package com.example.checkpoint2.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.checkpoint2.data.model.GoogleRepo
import com.example.checkpoint2.data.remote.GoogleRApi
import com.example.checkpoint2.data.remote.ReposApiService

class GoogleRepoSource(
    private val apiService: GoogleRApi
) : PagingSource<Int, GoogleRepo>() {
    override fun getRefreshKey(state: PagingState<Int, GoogleRepo>): Int? {
        return null
    }
/*
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GoogleRepo> {
        TODO("Not yet implemented")
    }*/


   override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GoogleRepo> {
       return try{
               val currentPage = params.key ?: 1
               val response = apiService.getGoogleRepo(currentPage)
                //val response = GoogleRApi().retrofitGoogleRepoService.getGoogleRepo(currentPage)
               val data = response.body()?.results ?: emptyList()
               val responseData = mutableListOf<GoogleRepo>()
               responseData.addAll(data)

               LoadResult.Page(
                   data = responseData,
                   prevKey = if (currentPage == 1) null else -1,
                   nextKey = currentPage.plus(1)
               )

            }
               catch (e:Exception)
                    {
                         LoadResult.Error(e)
                    }

}

}
