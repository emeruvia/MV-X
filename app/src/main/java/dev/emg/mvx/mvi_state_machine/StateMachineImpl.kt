package dev.emg.mvx.mvi_state_machine

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.emg.mvx.api.ApiState
import dev.emg.mvx.repository.RepositoryImpl
import dev.emg.mvx.repository.models.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import timber.log.Timber

class StateMachineImpl @AssistedInject constructor(
    @Assisted override val stateCallback: StateCallback<MVIState>,
    @Assisted private val scope: CoroutineScope,
    private val repository: RepositoryImpl,
) : StateMachine<MVIAction, MVIState>() {

    private val flowCollector = Job()

    override fun submit(action: MVIAction) {
        if (action is MVIAction.View) {
            when (action.viewAction) {
                is Action.Init -> {
                    flowCollector.cancel()
                    repository.fetchNationalPokedex()
                        .onEach {
                            when (it) {
                                is ApiState.Success -> {
                                    Timber.d("Success")
                                    stateCallback.state = MVIState.Success(it.data.pokemonEntries)
                                }
                            }
                        }.onStart {
                            Timber.d("Start")
                            stateCallback.state = MVIState.View(State.Loading)
                        }.catch { e ->
                            Timber.e(e.localizedMessage, "INIT state Machine")
                            stateCallback.state = MVIState.View(State.Error(e, e.localizedMessage))
                        }
                        .flowOn(Dispatchers.IO)
                        .launchIn(scope)
                }
                else -> {
                    Timber.d("Else")
                }
            }
        }
    }
}

sealed class MVIState {
    data class View(val viewState: State) : MVIState()
    data class Success(val data: List<Pokemon>) : MVIState()
}

sealed class MVIAction {
    data class View(val viewAction: Action<Unit>) : MVIAction()
}