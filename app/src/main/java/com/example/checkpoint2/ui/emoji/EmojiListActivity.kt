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
import com.example.checkpoint2.data.remote.EmojiApiService
import com.example.checkpoint2.data.remote.retrofit
import com.example.checkpoint2.databinding.ActivityEmojiListBinding



class EmojiListActivity : AppCompatActivity(){

    private lateinit var binding: ActivityEmojiListBinding
    private lateinit var adapter: EmojiAdapter
    private  val viewModel: EmojiListViewModel by viewModels()


    val apiService = retrofit.create(EmojiApiService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflate de emoji list layput
        binding = ActivityEmojiListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure the gridlayout with 4 colums
        binding.rvEmoji.layoutManager = GridLayoutManager(this,4)

        //get the emoji list
        var emojilist = viewModel.getEmojis()

        //put the data in the recyclerview
        binding.rvEmoji.adapter = adapter




        //remover
        //refresh

    }



}