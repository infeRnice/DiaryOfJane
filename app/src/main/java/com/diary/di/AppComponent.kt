package com.diary.di

import android.content.Context
import com.diary.ui.activities.MainActivity
import com.diary.ui.fragments.MainScreenFragment
import com.diary.ui.fragments.TaskCreationFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        DataModule::class,
        RepositoryModule::class
    ])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun injectToActivity(mainActivity: MainActivity)

    fun injectToMainFragment(mainScreenFragment: MainScreenFragment)
    fun injectToTaskCreationFragment(taskCreationFragment: TaskCreationFragment)

}