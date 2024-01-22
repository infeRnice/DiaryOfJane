package com.diary.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diary.domain.repository.DiaryRepository
import com.diary.ui.viewmodels.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repository: DiaryRepository
): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(repository)
                else -> throw IllegalArgumentException("Unknown ViewModel class")
            } as T
        }
}