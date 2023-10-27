package com.example.checkpoint2.ui.main


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import coil.load
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
        viewModel.getEmojis()


        //change the emoji
        binding.btRandomEmoji.setOnClickListener {


             //fornecer a lista de emojis
            //ir buscar o emoji a uma lista
            //colocar o emoji na imageview

            viewModel.getEmoji()

            val imgUrl = viewModel.emoji.value?.imgSrc

            val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
            binding.imageView.load(imgUri){}


        }


        //  navigate to the emoji list
        binding.btEmojiList.setOnClickListener {
            val navigateEmojiList = Intent(this,EmojiListActivity::class.java)
            startActivity(navigateEmojiList)
        }

        val textView = binding.textView
        val imgUrlAvatar = binding.imageView

        binding.btSeartch.setOnClickListener {

            val textoDigitado =    textView.editableText.toString()
            Log.d("TAG", "Texto digitado: $textoDigitado") //texto do user
            // verificar se há algum nome ("login") assim na lista dos users
            // se ouver tirar o ("avatar_ul") e colocar o msm na

            viewModel.seartchAvatar(textoDigitado)

            viewModel.avatarSearchResult.observe(this) { avatarList ->
                if (avatarList != null && avatarList.isNotEmpty()) {
                    val firstAvatar = avatarList.first()
                    imgUrlAvatar.setImageURI(Uri.parse(firstAvatar.avatarSrc))

                    // Salva a URL do avatar nas preferências compartilhadas para uso futuro.
                    val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("avatarUrl", firstAvatar.avatarSrc)
                    editor.apply()
                } else {

                    // Se a lista de avatares estiver vazia, define uma imagem de erro no ImageView.
                    imgUrlAvatar.setImageResource(R.drawable.error404)
                    Log.v("TAG","ERRO404")
                }

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


   }}