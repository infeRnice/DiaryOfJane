package com.diary.domain.repository

import com.diary.domain.models.Task

interface DiaryRepository {
    suspend fun getTaskById(id: Int): Task
    suspend fun addTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)
}