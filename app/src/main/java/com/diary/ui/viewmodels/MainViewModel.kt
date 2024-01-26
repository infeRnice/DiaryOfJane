package com.diary.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diary.domain.models.Task
import com.diary.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: DiaryRepository
) : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _calendarVisibility = MutableStateFlow(false)
    val calendarVisibility: StateFlow<Boolean> = _calendarVisibility.asStateFlow()

    init {
        loadTasksForDate(Date())
    }

    fun createTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
            // Здесь вы можете также обновить список задач, если это необходимо
        }
    }

    fun loadTasksForDate(date: Date) {
        viewModelScope.launch {
            val taskForDate = repository.getTaskByDate(date)
            _tasks.value = taskForDate
        }
    }

    fun toggleCalendarVisibility() {
        _calendarVisibility.value = !_calendarVisibility.value
        Log.d("ViewModel", "Calendar visibility toggled: ${_calendarVisibility.value}")
    }
}