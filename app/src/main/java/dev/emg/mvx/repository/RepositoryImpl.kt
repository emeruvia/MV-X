package dev.emg.mvx.repository

import dev.emg.mvx.api.ApiState
import dev.emg.mvx.api.PokeApi
import dev.emg.mvx.repository.models.Pokedex
import dev.emg.mvx.toPokedex
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: PokeApi
) : Repository {

    override fun fetchNationalPokedex() = flow<ApiState<Pokedex>> {
        val response = api.fetchNationalPokedex()
        emit(ApiState.Success(response.toPokedex()))
    }.catch { ex ->
        emit(ApiState.Error(Exception(ex.cause), ex.message))
    }.onStart {
        emit(ApiState.Loading)
    }

}