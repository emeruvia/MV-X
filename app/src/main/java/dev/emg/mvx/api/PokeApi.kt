package dev.emg.mvx.api

import dev.emg.mvx.PokedexResponse
import retrofit2.http.GET

interface PokeApi {

    @GET("pokedex/1/")
    suspend fun fetchNationalPokedex(): PokedexResponse

    companion object {
        const val BASE_URL = "pokeapi.co/api/v2/"
    }
}