package com.example.checkpoint2.ui.main


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.net.toUri
import coil.load
import com.example.checkpoint2.databinding.ActivityMainBinding
import com.example.checkpoint2.ui.avatar.AvatarListActivity
import com.example.checkpoint2.ui.emoji.EmojiListActivity
import com.example.checkpoint2.ui.repo.RepoListActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private val PREFS_FILEAVATAR = "avatarprefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getEmojis()

        binding.btRandomEmoji.setOnClickListener {
             //fornecer a lista de emojis
            //ir buscar o emoji a uma lista
            //colocar o emoji na imageview

            viewModel.getEmoji()

            val imgUrl = viewModel.emoji.value?.imgSrc

            val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
            binding.imageView.load(imgUri){}
        }


        binding.btEmojiList.setOnClickListener {
            val navigateEmojiList = Intent(this,EmojiListActivity::class.java)
            startActivity(navigateEmojiList)
        }

         val textView = binding.textView
        binding.btSeartch.setOnClickListener {

            val textoDigitado =    textView.editableText.toString()
            Log.d("TAG", "Texto digitado: $textoDigitado")

            viewModel.seartchAvatar(textoDigitado) { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }

            val imgUrl = viewModel.avatar.value?.avatarSrc

            val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
            binding.imageView.load(imgUri){}


            val sharedPreferences = this.getSharedPreferences(PREFS_FILEAVATAR,Context.MODE_PRIVATE) //Obtém uma referência às preferências compartilhadas para armazenar dados localmente
            val editor = sharedPreferences.edit()
            //Recupera a lista existente de avatares ou cria uma nova lista vazia
            val avatarUrls = sharedPreferences.getString("avatarUrls", null)?.split(",")?.toMutableList() ?: mutableListOf()

            // Adiciona a nova URL à lista
            if(!avatarUrls.contains(imgUrl)){
                if (imgUrl != null) {
                    avatarUrls.add(imgUrl)
                }
            }
            // Serializa a lista para uma string e a salva nas SharedPreferences
            val serializedList = avatarUrls.joinToString(separator = ",")
            editor.putString("avatarUrls", serializedList.trim(','))
            editor.apply()

            Log.v("TAG","$avatarUrls/n")


        }

        binding.btAvatarList.setOnClickListener {
            val navigateAvatarList = Intent(this, AvatarListActivity::class.java)
            startActivity(navigateAvatarList)
        }

        binding.btGoogleRepos.setOnClickListener {
            val navigateGoogleRepActivity = Intent(this,RepoListActivity::class.java)
            startActivity(navigateGoogleRepActivity)
        }
    }
}