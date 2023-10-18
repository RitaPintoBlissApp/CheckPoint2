package com.example.checkpoint2.ui.emoji

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.checkpoint2.R
import com.example.checkpoint2.data.model.Emoji
import com.example.checkpoint2.databinding.EmojiGridItemBinding

class EmojiAdapter : ListAdapter<Emoji, EmojiAdapter.EmojiViewHolder>(EmojiDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EmojiGridItemBinding.inflate(inflater, parent, false)
        return EmojiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmojiViewHolder, position: Int) {
        val emoji = getItem(position)
        holder.bind(emoji)
    }

    class EmojiViewHolder(private val binding: EmojiGridItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(emoji: Emoji) {
            binding.imageView.load(emoji.imgSrc) {
                // Configurações opcionais, por exemplo, placeholder, erro, etc.
            }
        }
    }
}
