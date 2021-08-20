package dev.emg.mvx.di.modules

import dagger.Binds
import dagger.Module
import dev.emg.mvx.repository.RepositoryImpl
import dev.emg.mvx.repository.Repository

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun providesMVVMRepository(repository: RepositoryImpl): Repository
}