package dev.emg.mvx.mvi_state_machine

abstract class StateMachine<Action, State> {
    protected abstract val stateCallback: StateCallback<State>
    abstract fun submit(action: Action)
}

interface StateCallback<State> {
    var state: State
}

sealed class State {
    object Init : State()
    object Loading : State()
    data class Error(val throwable: Throwable, val msg: String?) : State()
}

sealed class Action<D> {
    data class InitWith<InitialData>(val data: InitialData) : Action<InitialData>()
    object Init : Action<Unit>()
}