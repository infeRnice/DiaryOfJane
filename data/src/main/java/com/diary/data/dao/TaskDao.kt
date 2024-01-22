package com.diary.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.diary.data.model.TaskEntity

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: TaskEntity)

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    suspend fun getTaskById(id: Int): TaskEntity
}