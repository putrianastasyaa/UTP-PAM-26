package com.putri.todoapputp.data

import androidx.compose.runtime.mutableStateListOf
import com.putri.todoapputp.model.Task

object TaskRepository {

    private val tasks = mutableStateListOf<Task>()
    private var currentId = 0

    fun getTasks(): List<Task> = tasks

    fun addTask(title: String, desc: String, deadline: String) {
        tasks.add(
            Task(
                id = currentId++,
                title = title,
                description = desc,
                deadline = deadline
            )
        )
    }

    fun getTaskById(id: Int): Task? {
        return tasks.find { it.id == id }
    }

    fun updateTask(updated: Task) {
        val index = tasks.indexOfFirst { it.id == updated.id }
        if (index != -1) {
            tasks[index] = updated
        }
    }

    fun deleteTask(task: Task) {
        tasks.remove(task)
    }

}