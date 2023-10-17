package com.example.checkpoint2.ui.emoji

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.checkpoint2.R


class EmojiListActivity : AppCompatActivity(){

    private val viewModel: EmojiListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Substitua pelo seu layout

        val randomEmojiButton: Button = findViewById(R.id.btRandomEmoji)

        randomEmojiButton.setOnClickListener {
            val randomEmoji = viewModel.getRandomEmoji()
            if (randomEmoji != null) {
                // Exiba o emoji aleatório, por exemplo, em um TextView
                // textViewEmoji.text = randomEmoji.name
                Toast.makeText(this, randomEmoji.name, Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "Lista de emojis vazia", Toast.LENGTH_SHORT).show()
            }
        }
    }

}