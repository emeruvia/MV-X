package dev.emg.mvx.di.assisted

import dagger.assisted.AssistedFactory
import dev.emg.mvx.mvi.MVIState
import dev.emg.mvx.mvi.StateCallback
import dev.emg.mvx.mvi.StateMachineImpl
import kotlinx.coroutines.CoroutineScope

@AssistedFactory
interface StateMachineFactory {
    fun create(
        stateCallback: StateCallback<MVIState>,
        coroutineScope: CoroutineScope
    ): StateMachineImpl
}