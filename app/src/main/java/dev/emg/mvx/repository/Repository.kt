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

@Singleton
class Repository @Inject constructor(
    private val api: PokeApi
) {

    fun fetchNationalPokedex() = flow<ApiState<Pokedex>> {
        val response = api.fetchNationalPokedex()
        emit(ApiState.Success(response.toPokedex()))
    }.catch { ex ->
        emit(ApiState.Error(Exception(ex.cause), ex.message))
    }.onStart {
        emit(ApiState.Loading)
    }

}