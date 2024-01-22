package com.diary.di

import androidx.lifecycle.ViewModelProvider
import com.diary.di.factory.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {
    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory {
        return factory
    }
}