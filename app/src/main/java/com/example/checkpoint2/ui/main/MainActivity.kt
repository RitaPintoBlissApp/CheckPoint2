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

        val sharedPreferences = this.getSharedPreferences(PREFS_FILEAVATAR, Context.MODE_PRIVATE) //Obtém uma referência às preferências compartilhadas para armazenar dados localmente
        val editor = sharedPreferences.edit()

        val urlEmoji = sharedPreferences.getString("latestEmoji", null)
        val urlAvatar = sharedPreferences.getString("latestAvatar", null)

        Log.v("TAG","saved avatar: $urlAvatar")
        Log.v("TAG","saved emoji: $urlEmoji")

        //se houver dados vão ser inicializados
        if (urlEmoji != null || urlAvatar != null) {
            viewModel.initializeImageView(urlEmoji, urlAvatar)
        } else {
            viewModel.initializeImageView(null, null)
            Log.v("TAG", "Não há dados")
        }

        // Observa mudanças no emoji e atualiza a ImageView se um novo emoji for recebido
        viewModel.emoji.observe(this) { updateEmoji ->
            if (updateEmoji != null) {
                Log.v("TAG", "Emoji src: ${updateEmoji.imgSrc}")
                binding.imageView.load(updateEmoji.imgSrc.toUri().buildUpon().scheme("https").build()) {} //carrega a imagem
                editor.putString("latestEmoji", updateEmoji.imgSrc).apply() //Salva a última URL do emoji nas preferências compartilhadas
            }
            editor.remove("latestAvatar").apply()
        }

        // Observa mudanças no avatar e atualiza a ImageView se um novo avatar for recebido
        viewModel.avatar.observe(this) { updateAvatar ->
            if (updateAvatar != null) {
                binding.imageView.load(updateAvatar.avatarSrc.toUri().buildUpon().scheme("https").build()) {}
            }

            val imgUrl = updateAvatar?.avatarSrc

            //Recupera a lista existente de avatares ou cria uma nova lista vazia
            val avatarUrls = sharedPreferences.getString("avatarUrls", null)?.split(",")?.toMutableList()
                ?: mutableListOf()

            // Adiciona a nova URL à lista
            if (!avatarUrls.contains(imgUrl)) {
                if (imgUrl != null) {
                    avatarUrls.add(imgUrl)
                }
            }
            // Serializa a lista para uma string e a salva nas SharedPreferences
            val serializedList = avatarUrls.joinToString(separator = ",")
            editor.putString("avatarUrls", serializedList.trim(',')).apply()
            Log.v("TAG", "$avatarUrls\n")

            editor.putString("latestAvatar", imgUrl).apply()
            editor.remove("latestEmoji").apply()
        }



        binding.btRandomEmoji.setOnClickListener {
            viewModel.getEmoji()}

        binding.btEmojiList.setOnClickListener {
            val navigateEmojiList = Intent(this, EmojiListActivity::class.java)
            startActivity(navigateEmojiList)
        }

        val textView = binding.textView
        binding.btSearch.setOnClickListener {
            val textoDigitado = textView.editableText.toString()
            Log.d("TAG", "Texto digitado: $textoDigitado")

            viewModel.searchAvatar(textoDigitado) { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        }

        binding.btAvatarList.setOnClickListener {
            val navigateAvatarList = Intent(this, AvatarListActivity::class.java)
            startActivity(navigateAvatarList)
        }

        binding.btGoogleRepos.setOnClickListener {
            val navigateGoogleRepActivity = Intent(this, RepoListActivity::class.java)
            startActivity(navigateGoogleRepActivity)
        }
    }
}