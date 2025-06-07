package com.example.clean.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clean.databinding.FragmentCharacterListBinding
import com.example.clean.domain.model.Character
import com.example.clean.presentation.activity.MainActivity
import kotlinx.coroutines.flow.collectLatest
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
        val adapter = CharacterPagingAdapter { character: Character ->
            (activity as? MainActivity)?.openDetail(character.id)
        }

        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.charactersPagingFlow.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }

        // Состояние
        adapter.addLoadStateListener { loadState ->
            val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0

            binding.rvCharacters.visibility = if (isListEmpty) View.GONE else View.VISIBLE
            binding.tvEmpty.visibility = if (isListEmpty) View.VISIBLE else View.GONE
            binding.progressBar.visibility = if (loadState.refresh is LoadState.Loading) View.VISIBLE else View.GONE

            val errorState = when {
                loadState.prepend is LoadState.Error -> (loadState.prepend as LoadState.Error).error
                loadState.append is LoadState.Error -> (loadState.append as LoadState.Error).error
                loadState.refresh is LoadState.Error -> (loadState.refresh as LoadState.Error).error
                else -> null
            }

            errorState?.let { throwable ->
                Toast.makeText(
                    requireContext(),
                    "Ошибка загрузки: ${throwable.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
