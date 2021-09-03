package dev.emg.mvx

import android.app.Application
import dev.emg.mvx.di.AppComponent
import dev.emg.mvx.di.DaggerAppComponent
import timber.log.Timber.*

import timber.log.Timber

class App : Application() {

    val appComponent by lazy {
        initializeAppComponent()
    }

    open fun initializeAppComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

}