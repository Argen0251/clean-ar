package com.example.clean.presentation.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.clean.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel   // ← именно этот импорт!

class TapActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel: TapViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        viewModel.counter.observe(this) { counter ->
            binding.txCount.text = counter.count.toString()
        }

        binding.apply {
            btnDecrement.setOnClickListener {
                viewModel.decrement()
            }
            btnIncrement.setOnClickListener {
                viewModel.increment()
            }
        }
    }
}
