package dev.emg.mvx.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.emg.mvx.MainActivity
import dev.emg.mvx.di.modules.NetworkModule
import dev.emg.mvx.di.modules.RepositoryModule
import dev.emg.mvx.di.modules.ViewModelModule
import dev.emg.mvx.mvi.MVIStateMachineActivity
import dev.emg.mvx.mvvm.MVVMActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: MVVMActivity)
    fun inject(activity: MVIStateMachineActivity)
}