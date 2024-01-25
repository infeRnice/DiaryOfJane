package com.diary.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diary.R
import com.diary.domain.models.Task
import com.diary.ui.diffutils.TaskDiffCallback
import com.diary.ui.viewholders.DiaryViewHolder

class DiaryAdapter() : RecyclerView.Adapter<DiaryViewHolder>() {
    private var items: List<Task> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_cardview, parent, false)
        return DiaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    fun setItems(newItems: List<Task>) {
        val diffCallback = TaskDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }
}