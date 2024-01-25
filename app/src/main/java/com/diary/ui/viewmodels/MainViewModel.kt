package com.diary.ui.viewmodels

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

    private val _calendarVisibility = MutableStateFlow(true)
    val calendarVisibility: StateFlow<Boolean> = _calendarVisibility.asStateFlow()

    fun createTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
            // Здесь вы можете также обновить список задач, если это необходимо
        }
    }

    fun loadTasksForDate(date: Date) {
        // Загрузка задач для выбранной даты
        // Это может быть вызов к репозиторию, который будет получать данные
        // Здесь нужно использовать viewModelScope для запуска корутин
    }

    fun toggleCalendarVisibility() {
        _calendarVisibility.value = !_calendarVisibility.value
    }
}