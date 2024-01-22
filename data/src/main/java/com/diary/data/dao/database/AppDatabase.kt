package com.diary.data.dao.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diary.data.dao.TaskDao
import com.diary.data.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}