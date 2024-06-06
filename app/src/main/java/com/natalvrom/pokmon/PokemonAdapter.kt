package com.natalvrom.pokmon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class PokemonAdapter(
    private var pokemonList: List<PokemonItemResponse> = emptyList(),
    private val onClickListener: (PokemonItemResponse) -> Unit,
) : RecyclerView.Adapter<PokemonViewHolder>() {

    fun updateList(pokemonList: List<PokemonItemResponse>) {
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layout.inflate(R.layout.item_pokemon, parent, false))
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemonList[position]
        holder.bind(item, onClickListener)
    }

}







