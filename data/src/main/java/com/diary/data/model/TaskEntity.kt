package com.diary.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey val id: Int,
    val dateStart: Long,
    val dateFinish: Long,
    val name: String,
    val description: String
)
