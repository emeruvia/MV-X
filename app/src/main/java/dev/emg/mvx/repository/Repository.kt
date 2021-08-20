package dev.emg.mvx.repository

import dev.emg.mvx.PokedexResponse
import dev.emg.mvx.api.ApiState
import dev.emg.mvx.api.PokeApi
import dev.emg.mvx.repository.models.Pokedex
import dev.emg.mvx.toPokedex
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

interface Repository {
    fun fetchNationalPokedex(): Flow<ApiState<Pokedex>>
}