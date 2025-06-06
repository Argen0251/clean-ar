package com.example.clean.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.clean.databinding.FragmentCharacterDetailBinding
import com.example.clean.utils.UIState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.core.parameter.parametersOf

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding

    private val characterId: Int by lazy {
        requireArguments().getInt("character_id")
    }

    private val viewModel: CharacterDetailViewModel by stateViewModel { parametersOf(characterId) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.progressBar.visibility = View.GONE
        binding.tvError.visibility = View.GONE
        binding.containerContent.visibility = View.GONE

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
                viewModel.characterState.collect { state ->
                    when (state) {
                        is UIState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.tvError.visibility = View.GONE
                            binding.containerContent.visibility = View.GONE
                        }
                        is UIState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvError.visibility = View.GONE
                            binding.containerContent.visibility = View.VISIBLE

                            val character = state.data
                            binding.tvNameDetail.text = character.name
                            Glide.with(requireContext())
                                .load(character.image)
                                .into(binding.ivAvatarDetail)
                        }
                        is UIState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvError.visibility = View.VISIBLE
                            binding.containerContent.visibility = View.GONE
                            binding.tvError.text = "Ошибка: ${state.message}"
                        }
                        is UIState.Empty -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvError.visibility = View.VISIBLE
                            binding.tvError.text = "Данные не найдены"
                            binding.containerContent.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }


}
