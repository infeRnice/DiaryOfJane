package com.diary.di

import com.diary.data.dao.TaskDao
import com.diary.data.repository.DiaryRepositoryImpl
import com.diary.domain.repository.DiaryRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(taskDao: TaskDao): DiaryRepository {
        return DiaryRepositoryImpl(taskDao)
    }
}