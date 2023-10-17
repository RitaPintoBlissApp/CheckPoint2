package com.example.checkpoint2.ui.emoji

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.checkpoint2.R
import com.example.checkpoint2.databinding.ActivityEmojilistBinding


class EmojiListActivity : AppCompatActivity(){

    private lateinit var binding: ActivityEmojilistBinding
    //private  val viewModel: EmojiListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_emoji_list)
        binding = ActivityEmojilistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

}