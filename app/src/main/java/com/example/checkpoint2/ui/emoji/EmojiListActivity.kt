package com.example.checkpoint2.ui.emoji

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.checkpoint2.databinding.ActivityEmojiListBinding



class EmojiListActivity : AppCompatActivity(){

    private lateinit var binding: ActivityEmojiListBinding
    private  val viewModel: EmojiListViewModel = EmojiListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //adapter para a rv
        val adapter = EmojiAdapter{position ->
            viewModel.removeEmoji(position)
        }

        //"infla" o layout
        binding = ActivityEmojiListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configurar o lM e o adp para a rv
        binding.rvEmoji.layoutManager = GridLayoutManager(this,4)
        binding.rvEmoji.adapter = adapter

        //listener
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getEmojisFromApi(this)
        }
        //observer para todas as mudanças da lista + atualizações
        viewModel.emojiList.observe(this) { list ->
            if (list != null) {
                adapter.updateItems(list)
            }

            binding.swipeRefreshLayout.isRefreshing = false
        }


         //viewModel.getEmojis()
        viewModel.getEmojisFromCacheOrAPI(this)



    }




}