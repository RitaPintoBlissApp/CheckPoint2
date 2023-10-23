package com.example.checkpoint2.ui.avatar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.checkpoint2.databinding.ActivityAvatarListBinding

class AvatarListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAvatarListBinding
    private val viewModel: AvatarListViewModel = AvatarListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = AvatarAdapter{position ->
            viewModel.removeAvatar(position)
        }

        binding = ActivityAvatarListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAvatar.layoutManager = GridLayoutManager(this,4)

        binding.rvAvatar.adapter = adapter

        viewModel.avatarList.observe(this){ list->
            adapter.updateItem(list)
        }
        viewModel.getAvatar()
    }
}