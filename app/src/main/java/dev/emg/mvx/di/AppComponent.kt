package dev.emg.mvx.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.emg.mvx.MainActivity
import dev.emg.mvx.mvvm.MVVMActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: MVVMActivity)
}