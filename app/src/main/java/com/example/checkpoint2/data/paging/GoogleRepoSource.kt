package com.example.checkpoint2.data.paging
/*
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.checkpoint2.data.model.GoogleRepo
import com.example.checkpoint2.data.remote.ReposApiService

@Suppress("UNREACHABLE_CODE")
class GoogleRepoSource(private val apiService: ReposApiService) : PagingSource<Int, GoogleRepo>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GoogleRepo> {
        return try {
            // Start refresh at page 1 if undefined.
            val currentPage = params.key ?: 1
            val response = apiService.getGoogleRepo(currentPage)
            //val response = GoogleRApi().retrofitGoogleRepoService.getGoogleRepo(currentPage)
            val data = response.body()?.results ?: emptyList()
            val responseData = mutableListOf<GoogleRepo>()
            responseData.addAll(data)

            return LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, GoogleRepo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

}
*/