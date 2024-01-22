package com.diary.ui.compose_components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.diary.domain.models.Task

@Composable
fun TaskCreationScreen(onTaskCreated: (Task) -> Unit) {
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var dateStart by remember { mutableStateOf(System.currentTimeMillis()) }
    var dateFinish by remember { mutableStateOf(System.currentTimeMillis()) }

    Column {
        TextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("Название задачи") }
        )
        TextField(
            value = taskDescription,
            onValueChange = { taskDescription = it },
            label = { Text("Описание задачи") }
        )
        Button(onClick = {
            // Здесь может быть ваша логика валидации
            onTaskCreated(
                Task(
                id = 0, // ID должен генерироваться базой данных, если он autoincrement
                dateStart = dateStart,
                dateFinish = dateFinish,
                name = taskName,
                description = taskDescription
            )
            )
        }) {
            Text("Создать задачу")
        }
    }
}
