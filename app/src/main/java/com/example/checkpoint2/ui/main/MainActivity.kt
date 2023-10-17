package com.example.checkpoint2.ui.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.checkpoint2.data.remote.EmojiApiService
import com.example.checkpoint2.data.remote.retrofit
import com.example.checkpoint2.databinding.ActivityMainBinding
import com.example.checkpoint2.ui.avatar.AvatarListViewModel
import com.example.checkpoint2.ui.emoji.EmojiListViewModel
import com.example.checkpoint2.ui.emoji.EmojiViewModelFactory
import com.example.checkpoint2.ui.repo.RepoListActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var emojiListViewModel: EmojiListViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = retrofit.create(EmojiApiService::class.java)
        emojiListViewModel = ViewModelProvider(this, EmojiViewModelFactory(apiService)).get(
            EmojiListViewModel::class.java)

        //change the emoji
        binding.btRandomEmoji.setOnClickListener {

        emojiListViewModel.getEmojis()

        }


        //  navigate to the emoji list
        binding.btEmojiList.setOnClickListener {
            val navigateEmojiList = Intent(this, emojiListViewModel::class.java)
            startActivity(navigateEmojiList)
        }


        //  navigate to the avatar list
        binding.btAvatarList.setOnClickListener {
            val navigateEmojiList = Intent(this, AvatarListViewModel::class.java)
            startActivity(navigateEmojiList)
        }

        //to Google Rep
        binding.btGoogleRepos.setOnClickListener {
            val navigateEmojiList = Intent(this, RepoListActivity::class.java)
            startActivity(navigateEmojiList)
        }

    }


   }