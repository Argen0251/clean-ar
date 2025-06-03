package com.example.clean.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clean.databinding.FragmentCharacterListBinding
import com.example.clean.presentation.fragment.CharacterAdapter
import com.example.clean.presentation.fragment.CharacterListViewModel
import com.example.clean.presentation.activity.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding

    private val viewModel: CharacterListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        viewModel.characters.observe(viewLifecycleOwner) { list ->
            binding.rvCharacters.adapter = CharacterAdapter(list) { character ->
                (activity as? MainActivity)?.openDetail(character.id)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { message ->
            if (message.isNotEmpty()) {
                 }
        }
    }


}
