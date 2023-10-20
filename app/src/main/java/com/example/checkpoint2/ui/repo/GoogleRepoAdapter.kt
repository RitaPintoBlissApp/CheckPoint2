package com.example.checkpoint2.ui.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.checkpoint2.R
import com.example.checkpoint2.data.model.Emoji
import com.example.checkpoint2.data.model.GoogleRepo

class GoogleRepoAdapter(
    private val onItemClick: (position: Int) -> Unit
): RecyclerView.Adapter<GoogleRepoAdapter.ViewHolderEatchGoogleRepo>() {

    private val googelRepoList = mutableListOf<GoogleRepo>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderEatchGoogleRepo {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.googlerepo_grid_item,parent,false)
        return ViewHolderEatchGoogleRepo(view)
    }

    override fun onBindViewHolder(
        holder:ViewHolderEatchGoogleRepo,
        position: Int)
    {
        val googlerepo = googelRepoList[position]
        holder.bind(googlerepo)
    }

    override fun getItemCount(): Int {
       return googelRepoList.size
    }
    inner class ViewHolderEatchGoogleRepo(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ttextView: TextView = itemView.findViewById((R.id.titleTextView))
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