package com.example.checkpoint2.ui.emoji

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.example.checkpoint2.R
import com.example.checkpoint2.data.model.Emoji
import com.example.checkpoint2.data.remote.EmojiApiService
import com.example.checkpoint2.data.remote.retrofit
import com.example.checkpoint2.databinding.ActivityEmojiListBinding



class EmojiListActivity : AppCompatActivity(){

    private lateinit var binding: ActivityEmojiListBinding
    private  val viewModel: EmojiListViewModel = EmojiListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = EmojiAdapter{position ->
            viewModel.removeEmoji(position)
        }

        //inflate de emoji list layput
        binding = ActivityEmojiListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure the gridlayout with 4 colums
        binding.rvEmoji.layoutManager = GridLayoutManager(this,4)

        //put the data in the recyclerview
        binding.rvEmoji.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getEmojis()
        }

        viewModel.emojiList.observe(this) { list ->
            adapter.updateItems(list)

            binding.swipeRefreshLayout.isRefreshing = false
        }

        //get the emoji list
         viewModel.getEmojis()



    }




}