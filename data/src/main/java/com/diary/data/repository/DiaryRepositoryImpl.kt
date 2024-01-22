package com.diary.data.repository

import com.diary.data.dao.TaskDao
import com.diary.data.model.TaskEntity
import com.diary.domain.models.Task
import com.diary.domain.repository.DiaryRepository
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : DiaryRepository {

    override suspend fun addTask(task: Task) {
        val taskEntity = convertDomainToEntity(task)
        taskDao.insertTask(taskEntity)
    }

    override suspend fun getTaskById(id: Int): Task {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(task: Task) {
        TODO("Not yet implemented")
    }

    fun convertEntityToDomain(taskEntity: TaskEntity): Task {
        return Task(
            id = taskEntity.id,
            dateStart = taskEntity.dateStart,
            dateFinish = taskEntity.dateFinish,
            name = taskEntity.name,
            description = taskEntity.description
        )
    }

    fun convertDomainToEntity(task: Task): TaskEntity {
        return TaskEntity(
            id = task.id,
            dateStart = task.dateStart,
            dateFinish = task.dateFinish,
            name = task.name,
            description = task.description
        )
    }
}