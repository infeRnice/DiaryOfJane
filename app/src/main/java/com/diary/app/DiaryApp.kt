package com.diary.app

import android.app.Application
import androidx.room.Room
import com.diary.data.dao.database.AppDatabase
import com.diary.di.AppComponent
import com.diary.di.DaggerAppComponent

class DiaryApp: Application() {

    lateinit var diaryAppComponent: AppComponent
    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()

        diaryAppComponent = DaggerAppComponent.builder()
            .context(this)
            .build()

        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).addMigrations(AppDatabase.MIGRATION_1_2)
            .build()
    }
}