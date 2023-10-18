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

        binding = ActivityEmojiListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvEmoji.layoutManager = GridLayoutManager(this,4)

        // Configurar o adapter
        adapter = EmojiAdapter()
        binding.rvEmoji.adapter = adapter

        //gerar a lista de emojis
        viewModel.getEmojis()

        // Observar as mudanças na lista de emojis
        viewModel.emojiList.observe(this, { emojis ->
            // Atualizar o adapter com a nova lista de emojis
            adapter.submitList(emojis)
        })
        //remover
        //refresh

    }



}