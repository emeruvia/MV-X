package dev.emg.mvx.repository

import dev.emg.mvx.api.ApiState
import dev.emg.mvx.repository.models.Pokedex
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchNationalPokedex(): Flow<ApiState<Pokedex>>
}