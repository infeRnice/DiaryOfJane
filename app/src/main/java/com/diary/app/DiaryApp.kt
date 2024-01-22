package com.diary.app

import android.app.Application
import com.diary.di.AppComponent
import com.diary.di.DaggerAppComponent

class DiaryApp: Application() {

    lateinit var diaryAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        diaryAppComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}