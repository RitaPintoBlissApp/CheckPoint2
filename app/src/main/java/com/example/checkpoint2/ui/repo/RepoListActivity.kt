package com.example.checkpoint2.ui.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checkpoint2.databinding.ActivityGoogleRepoBinding



class RepoListActivity : AppCompatActivity(){

    private lateinit var binding: ActivityGoogleRepoBinding
    private val viewModel: RepoListViewModel = RepoListViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = GoogleRepoAdapter{}

        binding = ActivityGoogleRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvGoogleRepos.layoutManager =  LinearLayoutManager(this)

        binding.rvGoogleRepos.adapter = adapter

        viewModel.googleRepoList.observe(this){list ->
           adapter.updateItem(list)
        }

        viewModel.getGoogleRepo()

    }

}




/** binding = ActivityGoogleRepoBinding.inflate(layoutInflater)
setContentView(binding.root)

setupRV()
loadingData()


private fun loadingData(){
lifecycleScope.launch {
viewModel.listData.collect{ pagingData->
mAdapter.submitData(pagingData)
}
}
}

private fun setupRV(){
mAdapter = GoogleRepoAdapter()
binding.rvGoogleRepos.apply {
layoutManager = StaggeredGridLayoutManager(
2,StaggeredGridLayoutManager.VERTICAL
)
adapter=mAdapter
setHasFixedSize(true)
}
}*/