package com.diary.di

import android.content.Context
import com.diary.data.dao.TaskDao
import com.diary.data.dao.database.AppDatabase
import dagger.Module
import dagger.Provides
import androidx.room.Room

@Module
class DataModule {

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }

}