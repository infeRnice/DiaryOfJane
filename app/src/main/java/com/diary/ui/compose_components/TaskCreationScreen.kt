package com.diary.ui.compose_components

import android.app.Activity
import android.app.DatePickerDialog
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.diary.domain.models.Task
import java.util.Calendar
import java.util.Date

@Composable
fun TaskCreationScreen(onTaskCreated: (Task) -> Unit) {
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var dateStart by remember { mutableStateOf<Date?>(null) }
    var dateFinish by remember { mutableStateOf<Date?>(null) }

    val context = LocalContext.current

    val showStartDatePicker = remember { mutableStateOf(false) }
    val showFinishDatePicker = remember { mutableStateOf(false) }

    if (showStartDatePicker.value) {
        DatePickerDialog(
            context, { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                dateStart = calendar.time
                showStartDatePicker.value = false
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    if (showFinishDatePicker.value) {
        DatePickerDialog(
            context, { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                dateFinish = calendar.time
                showFinishDatePicker.value = false
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .wrapContentSize(Alignment.Center)
    ) {
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
        Button(onClick = { showStartDatePicker.value = true }) {
            Text("Выбрать начальную дату")
        }
        Button(onClick = { showFinishDatePicker.value = true }) {
            Text("Выбрать конечную дату")
        }
        Button(onClick = {
            if (dateStart != null && dateFinish != null) {
                onTaskCreated(
                    Task(
                        id = 0, // Удалите эту строку, если ваша база данных автоматически генерирует ID
                        dateStart = dateStart!!.time,
                        dateFinish = dateFinish!!.time,
                        name = taskName,
                        description = taskDescription
                    )
                )
            } else {
                // Обработайте случай, когда даты не выбраны
            }
        }) {
            Text("Создать задачу")
        }
    }
}
