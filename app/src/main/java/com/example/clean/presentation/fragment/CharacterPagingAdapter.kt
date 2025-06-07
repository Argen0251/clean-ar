package com.example.clean.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clean.databinding.ItemCharacterBinding
import com.example.clean.domain.model.Character

class CharacterPagingAdapter(
    private val onItemClick: (Character) -> Unit
) : PagingDataAdapter<Character, CharacterPagingAdapter.VH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(a: Character, b: Character) = a.id == b.id
            override fun areContentsTheSame(a: Character, b: Character) = a == b
        }
    }

    inner class VH(private val binding: ItemCharacterBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character?) {
            item?.let { ch ->
                binding.tvNameItem.text = ch.name
                Glide.with(binding.ivAvatarItem.context)
                    .load(ch.image)
                    .into(binding.ivAvatarItem)

                binding.root.setOnClickListener { onItemClick(ch) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}