package com.example.clean.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.clean.databinding.FragmentCharacterDetailBinding
import com.example.clean.presentation.activity.CharacterDetailViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.core.parameter.parametersOf

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding

    private val characterId: Int by lazy {
        requireArguments().getInt("character_id")
    }

    private val viewModel: CharacterDetailViewModel by stateViewModel {
        parametersOf(characterId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.character.observe(viewLifecycleOwner) { character ->
            binding.tvNameDetail.text = character.name
            Glide.with(requireContext())
                .load(character.image)
                .into(binding.ivAvatarDetail)
        }

        viewModel.error.observe(viewLifecycleOwner) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(requireContext(), "Ошибка: $message", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
