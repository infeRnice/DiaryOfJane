package com.diary.ui.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diary.R
import com.diary.domain.models.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DiaryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val textViewTaskName: TextView = view.findViewById(R.id.textViewTaskName)
    private val textViewTaskTime: TextView = view.findViewById(R.id.textViewTaskTime)

    fun bind(task: Task) {
        textViewTaskName.text = task.name
        textViewTaskTime.text = formatTime(task.dateStart, task.dateFinish)
    }

    private fun formatTime(start: Long, finish: Long): String {
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatter.format(Date(start)) + "-" + formatter.format(Date(finish))
    }
}