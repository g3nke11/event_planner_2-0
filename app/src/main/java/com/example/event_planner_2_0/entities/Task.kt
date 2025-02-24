package com.example.event_planner_2_0.entities

import java.time.LocalTime

//class Task(
//    val description: String,
//    val startTime: LocalTime,
//    val endTime: LocalTime) {
////    override fun toString(): String {
////        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
////        return "Task: $description, Start: ${startTime.format(timeFormatter)}, End: ${endTime.format(timeFormatter)}"
////    }
//}

data class Task(
    val name: String,
    val description: String,
    val completed: Boolean = false
)