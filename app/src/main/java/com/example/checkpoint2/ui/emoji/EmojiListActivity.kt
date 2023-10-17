package com.example.checkpoint2.ui.emoji

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.checkpoint2.R
import com.example.checkpoint2.databinding.ActivityEmojiListBinding



class EmojiListActivity : AppCompatActivity(){

    private lateinit var binding: ActivityEmojiListBinding
    //private  val viewModel: EmojiListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_emoji_list)
        binding = ActivityEmojiListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

}