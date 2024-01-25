package com.diary.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.diary.data.model.TaskEntity
import com.diary.domain.models.Task
import java.util.Date

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: TaskEntity)

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    suspend fun getTaskById(id: Int): TaskEntity

    @Query("SELECT * FROM TaskEntity WHERE dateStart <= :date AND dateFinish >= :date")
    suspend fun getTaskByDate(date: Long): List <TaskEntity>

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}