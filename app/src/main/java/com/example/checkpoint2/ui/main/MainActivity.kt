package com.example.checkpoint2.ui.main


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.checkpoint2.R
import com.example.checkpoint2.data.model.Emoji
import com.example.checkpoint2.data.remote.EmojiApi
import com.example.checkpoint2.data.remote.EmojiApiService
import com.example.checkpoint2.data.remote.retrofit
import com.example.checkpoint2.databinding.ActivityMainBinding
import com.example.checkpoint2.ui.avatar.AvatarListActivity
import com.example.checkpoint2.ui.emoji.EmojiListActivity
import com.example.checkpoint2.ui.repo.RepoListActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = retrofit.create(EmojiApiService::class.java)
        //change the emoji
        binding.btRandomEmoji.setOnClickListener {


        /*
        * checkpoint 1
        *  btnChangeEmoji.setOnClickListener {
            val randomIndex = (emojis.indices).random()
            val randomemoji = emojis[randomIndex]
            imgEmoji.setImageResource(randomemoji)
        }
*/

            //fornecer a lista de emojis
            val emojis = viewModel.getEmojis()
            //ir buscar o emoji a uma lista
            val emoji = emojis.random()
            //colocar o emoji na imageview
            binding.imageView.setImageURI(Uri.parse(emoji.url))

        }


        //  navigate to the emoji list
        binding.btEmojiList.setOnClickListener {
            val navigateEmojiList = Intent(this,EmojiListActivity::class.java)
            startActivity(navigateEmojiList)
        }


        //  navigate to the avatar list
        binding.btAvatarList.setOnClickListener {
            val navigateAvatarList = Intent(this, AvatarListActivity::class.java)
            startActivity(navigateAvatarList)
        }

        //to Google Rep
        binding.btGoogleRepos.setOnClickListener {
            val navigateGoogleRepActivity = Intent(this,RepoListActivity::class.java)
            startActivity(navigateGoogleRepActivity)
        }

    }


   }