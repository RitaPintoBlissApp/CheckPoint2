package com.example.checkpoint2.ui.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.checkpoint2.data.remote.GithubApiService
import com.example.checkpoint2.data.remote.retrofit
import com.example.checkpoint2.databinding.ActivityMainBinding
import com.example.checkpoint2.ui.emoji.EmojiViewModel
import com.example.checkpoint2.ui.emoji.EmojiViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var emojiViewModel: EmojiViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = retrofit.create(GithubApiService::class.java)
        emojiViewModel = ViewModelProvider(this, EmojiViewModelFactory(apiService)).get(
            EmojiViewModel::class.java)

        //change the emoji
        binding.btRandomEmoji.setOnClickListener {

        emojiViewModel.searchEmojis()

        }




        //  navigate to the emoji list
        binding.btEmojiList.setOnClickListener {

        }


        //  navigate to the avatar list
        binding.btAvatarList.setOnClickListener {

        }

        //to Google Rep
        binding.btGoogleRepos.setOnClickListener {

        }

    }


   }