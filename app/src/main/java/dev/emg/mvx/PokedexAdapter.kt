package dev.emg.mvx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.emg.mvx.repository.models.Pokemon

class PokedexAdapter() : ListAdapter<Pokemon, PokemonViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}

class PokemonViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {

    private val name: TextView by lazy { item.findViewById<TextView>(R.id.name) }
    private val entry: TextView by lazy { item.findViewById<TextView>(R.id.entry_number) }

    fun bind(pokemon: Pokemon) {
        name.text = pokemon.name
        entry.text = pokemon.entryNumber.toString()
    }

    companion object {
        fun from(parent: ViewGroup): PokemonViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_pokemon, parent, false)
            return PokemonViewHolder(view)
        }
    }
}