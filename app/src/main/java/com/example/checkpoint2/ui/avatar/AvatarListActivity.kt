package com.example.checkpoint2.ui.avatar

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.checkpoint2.databinding.ActivityAvatarListBinding

class AvatarListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAvatarListBinding
    private val viewModel: AvatarListViewModel = AvatarListViewModel()
    private val PREFS_FILEAVATAR = "avatarprefs"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var adapter = AvatarAdapter{ position ->
            viewModel.removeAvatar(position)
        }

        binding = ActivityAvatarListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAvatar.layoutManager = GridLayoutManager(this,4)

        binding.rvAvatar.adapter = adapter

        viewModel.avatarList.observe(this){ list->
            adapter.updateItem(list)
        }
        viewModel.getAvatarFromPrefs(this)

       // val prefs:SharedPreferences = getSharedPreferences(PREFS_FILEAVATAR, Context.MODE_PRIVATE)
        //val savedURL = prefs.getString("avatarUrl","N/A") ?: "N/A"

        //Log.d("TAG", "Texto digitado avatar list: $savedURL")

        // Atualize a lista do Adapter com os dados carregados
        //adapter.updateItem(listOf(savedURL))
    }
}