package dev.emg.mvx.di.assisted

import dagger.assisted.AssistedFactory
import dev.emg.mvx.mvi_state_machine.MVIState
import dev.emg.mvx.mvi_state_machine.StateCallback
import dev.emg.mvx.mvi_state_machine.StateMachineImpl
import kotlinx.coroutines.CoroutineScope


@AssistedFactory
interface StateMachineFactory {
    fun create(
        stateCallback: StateCallback<MVIState>,
        coroutineScope: CoroutineScope
    ): StateMachineImpl
}