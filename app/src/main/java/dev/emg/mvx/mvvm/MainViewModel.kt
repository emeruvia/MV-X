package dev.emg.mvx.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emg.mvx.api.ApiState
import dev.emg.mvx.repository.Repository
import dev.emg.mvx.repository.models.Pokedex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _pokedex = MutableStateFlow<ApiState<Pokedex>>(ApiState.Loading)
    val pokedex: StateFlow<ApiState<Pokedex>> = _pokedex

    init {
        viewModelScope.launch {
            repository.fetchNationalPokedex().collect {
                _pokedex.value = it
            }
        }
    }
}