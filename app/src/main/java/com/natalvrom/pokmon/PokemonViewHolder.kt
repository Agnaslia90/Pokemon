package com.natalvrom.pokmon

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.natalvrom.pokmon.databinding.ItemPokemonBinding

class PokemonViewHolder(view:View): RecyclerView.ViewHolder (view){
    private val binding = ItemPokemonBinding.bind(view)

    fun bind(
        pokemon: PokemonItemResponse,
        onClickListener: (PokemonItemResponse) -> Unit
    ) {
        binding.tvPokemonName.text = pokemon.name
        binding.cvPokemonList.setOnClickListener {
            onClickListener(pokemon)
        }
    }

}