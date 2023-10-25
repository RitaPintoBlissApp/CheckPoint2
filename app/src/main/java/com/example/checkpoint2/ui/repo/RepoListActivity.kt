package com.example.checkpoint2.ui.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val reposLayoutManager = binding.rvGoogleRepos.layoutManager as LinearLayoutManager

        binding.rvGoogleRepos.adapter = adapter

        viewModel.googleRepoList.observe(this){list ->
           adapter.updateItem(list)
        }

        viewModel.getGoogleRepo()

        //importante
        //deteta o scroll feito (com o listener de scroll) e quando chega ao fim da pagina
        //carrega mais items para o final da lista para continuar o scroll
        binding.rvGoogleRepos.addOnScrollListener(object : RecyclerView.OnScrollListener(){
             private fun update(){
                 viewModel.getNextGoogleRepos()
                 //para de acrescentar
             }

            //verifica se chegou ao fim da lista
            override fun onScrolled(recyclerView: RecyclerView, dx:Int, dy:Int){
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount: Int = reposLayoutManager.itemCount
                val lastVisibleItemIndex :Int = reposLayoutManager.findLastVisibleItemPosition()
                if(lastVisibleItemIndex == totalItemCount-1){ // é o ultimo item?
                    update() //acrescenta mais
                }
            }
        })

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