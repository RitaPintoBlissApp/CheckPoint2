package com.example.checkpoint2.ui.avatar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.checkpoint2.R
import com.example.checkpoint2.data.model.Avatar


class AvatarAdapter(
    private val onItemClick: (position: Int) -> Unit)
    : RecyclerView.Adapter<AvatarAdapter.ViewHolderEatchAvatar>() {

    private val avatarList = mutableListOf<Avatar>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderEatchAvatar {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.avatar_grid_item, parent, false)
        return ViewHolderEatchAvatar(view)
    }

    override fun onBindViewHolder(holder: ViewHolderEatchAvatar, position: Int) {
        val avatar = avatarList[position]
        holder.bind(avatar)

        holder.itemView.setOnClickListener {//add a listener
            avatarList.removeAt(position)
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return avatarList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItem(list: List<Avatar>?) {
        avatarList.clear()
        if (list != null) {
            avatarList.addAll(list)
        }
        notifyDataSetChanged()
    }


    inner class ViewHolderEatchAvatar (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)

        init {
            itemView.setOnClickListener {
                onItemClick(bindingAdapterPosition)
            }
        }

        fun bind(avatar: Avatar) {
            avatarImageView.load(avatar.avatarSrc)
        }


    }
}