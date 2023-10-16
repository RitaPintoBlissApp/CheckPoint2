package com.example.checkpoint2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.checkpoint2.databinding.ActivityMainBinding
import com.example.checkpoint2.network.Emoji
import com.example.checkpoint2.network.EmojiViewModel
import com.example.checkpoint2.network.GithubApiService
import com.example.checkpoint2.network.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var emojiViewModel: EmojiViewModel

    val apiService = retrofit.create(GithubApiService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emojiViewModel = ViewModelProvider(this, EmojiViewModelFactory(apiService)).get(EmojiViewModel::class.java)

        //change the emoji
        binding.btRandomEmoji.setOnClickListener {

        emojiViewModel.SearchEmojis()
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