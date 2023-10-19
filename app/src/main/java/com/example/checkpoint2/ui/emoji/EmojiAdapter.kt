package com.example.checkpoint2.ui.emoji

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.checkpoint2.R
import com.example.checkpoint2.data.model.Emoji
import com.example.checkpoint2.databinding.EmojiGridItemBinding

class EmojiAdapter(
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<EmojiAdapter.ViewHolderEatchEmoji>() {

    private val emojiList = mutableListOf<Emoji>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderEatchEmoji {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.emoji_grid_item, parent,false)
        return  ViewHolderEatchEmoji(view)
    }


    //puts the emoji in a position
    override fun onBindViewHolder(holder: ViewHolderEatchEmoji, position: Int) {
        val emoji = emojiList[position]
        holder.bind(emoji)

        holder.itemView.setOnClickListener {
            emojiList.removeAt(position)
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return emojiList.size
    }


    //viewholder is a helper class that holds the View of a row or rows
    inner class ViewHolderEatchEmoji(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val emojiImageView: ImageView = itemView.findViewById((R.id.emoji_image))
        init {
            itemView.setOnClickListener{
                onItemClick(bindingAdapterPosition)
            }
        }
        //attributes an imagem to eatch ImageView
        fun bind(emoji: Emoji) {
            //val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
            emojiImageView.load(emoji.imgSrc)

        }
    }

    public fun updateItems(  emoji: List<Emoji> ){
        emojiList.clear()
        emojiList.addAll(emoji)
        notifyDataSetChanged()
    }

}