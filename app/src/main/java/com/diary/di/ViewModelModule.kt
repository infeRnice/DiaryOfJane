package com.diary.di

import com.diary.di.factory.ViewModelFactory
import com.diary.domain.repository.DiaryRepository
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideViewModelFactory(repository: DiaryRepository): ViewModelFactory {
        return ViewModelFactory(repository)
    }
}