package com.example.checkpoint2.ui.repo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.checkpoint2.R
import com.example.checkpoint2.data.model.GoogleRepo


class GoogleRepoAdapter(
    private val onItemClick: (position: Int) -> Unit
): RecyclerView.Adapter<GoogleRepoAdapter.ViewHolderEatchGoogleRepo>() {

    private val googleRepoList = mutableListOf<GoogleRepo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderEatchGoogleRepo {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.googlerepo_grid_item,parent,false)
        return ViewHolderEatchGoogleRepo(view)
    }

    override fun onBindViewHolder(
        holder:ViewHolderEatchGoogleRepo,
        position: Int)
    {
        val googlerepo = googleRepoList[position]
        holder.bind(googlerepo)


        holder.itemView.setOnClickListener{
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return googleRepoList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateItem(list: List<GoogleRepo>?){
        googleRepoList.clear()
        if(list !=null){
            googleRepoList.addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolderEatchGoogleRepo(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ttextView: TextView = itemView.findViewById((R.id.textView))
        init {
            itemView.setOnClickListener{
                onItemClick(bindingAdapterPosition)
            }
        }
        //attributes an imagem to eatch ImageView
        fun bind(repo: GoogleRepo) {
            //val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
            ttextView.text = repo.fullName
        }
    }

}


/*class GoogleRepoAdapter: PagingDataAdapter<GoogleRepo,GoogleRepoAdapter.MyViewHolder>(diffcallback){

    inner class MyViewHolder(val binding: GooglerepoGridItemBinding):
    RecyclerView.ViewHolder(binding.root)
    companion object {
        val diffcallback = object :DiffUtil.ItemCallback<GoogleRepo>(){
            override fun areItemsTheSame(oldItem: GoogleRepo, newItem: GoogleRepo): Boolean {
              return  oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GoogleRepo, newItem: GoogleRepo): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val currentItem = getItem(position)

        //this method getItem() is from PagingDataAdapter...
        holder.binding.apply {
            textView.text = "${currentItem?.fullName}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(
           GooglerepoGridItemBinding.inflate(
           LayoutInflater.from(parent.context),
           parent, false))

    }
}*/