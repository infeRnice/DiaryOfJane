package com.diary.domain.repository

import com.diary.domain.models.Task
import java.util.Date

interface DiaryRepository {
    suspend fun getTaskById(id: Int): Task

    suspend fun getTaskByDate(date: Date): List<Task>
    suspend fun addTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)
}