package com.example.clean.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clean.databinding.FragmentCharacterListBinding
import com.example.clean.presentation.activity.MainActivity
import com.example.clean.utils.UIState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
            adapter = CharacterAdapter(emptyList()) { }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
                viewModel.charactersState.collect { state ->
                    when (state) {
                        is UIState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.rvCharacters.visibility = View.GONE
                        }
                        is UIState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rvCharacters.visibility = View.VISIBLE

                            val listOfCharacters = state.data
                            binding.rvCharacters.adapter = CharacterAdapter(listOfCharacters) { character ->
                                (activity as? MainActivity)?.openDetail(character.id)
                            }
                        }
                        is UIState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rvCharacters.visibility = View.GONE

                            Toast.makeText(
                                requireContext(),
                                "Ошибка: ${state.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is UIState.Empty -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rvCharacters.visibility = View.GONE
                            binding.tvEmpty.visibility = View.VISIBLE
                            binding.tvEmpty.text = "Персонажей не найдено"
                        }
                    }
                }
            }
        }

    }

}
