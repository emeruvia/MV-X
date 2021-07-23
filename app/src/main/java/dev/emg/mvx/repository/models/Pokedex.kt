package dev.emg.mvx.repository.models

data class Pokedex(
    val id: Long,
    val name: String,
    val pokemonEntries: List<Pokemon>
)

data class Pokemon(
    val entryNumber: Long,
    val name: String,
    val url: String
)

