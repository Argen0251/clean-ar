package com.example.clean.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clean.R
import com.example.clean.databinding.ActivityMainBinding
import com.example.clean.presentation.fragment.CharacterDetailFragment
import com.example.clean.presentation.fragment.CharacterListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main, CharacterListFragment())
                .commit()
        }
    }

    fun openDetail(characterId: Int) {
        val detailFragment = CharacterDetailFragment()

        val args = Bundle().apply {
            putInt("character_id", characterId)
        }
        detailFragment.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}
