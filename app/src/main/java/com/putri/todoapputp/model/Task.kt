package com.putri.todoapputp.model

data class Task(
    val id: Int,
    var title: String,
    var description: String,
    var deadline: String,
    var isDone: Boolean = false
)