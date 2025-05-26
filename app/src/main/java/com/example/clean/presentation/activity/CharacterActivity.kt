package com.example.clean.presentation.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.clean.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel   // ← именно этот импорт!

class CharacterActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel: CharacterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        viewModel.character.observe(this) { ch ->
            binding.tvName.text = ch.name
            Glide.with(this)
                .load(ch.image)
                .circleCrop()
                .into(binding.ivAvatar)
        }

        viewModel.loadData()

    }
}

