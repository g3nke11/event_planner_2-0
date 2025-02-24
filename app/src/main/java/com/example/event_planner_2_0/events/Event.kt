package com.example.event_planner_2_0.events

import com.example.event_planner_2_0.entities.Task
import java.time.LocalDate
import java.time.LocalTime

data class Event(
    val id: String = "", // Add id field to represent the Firestore document ID
    val type: String,
    val title: String,
    val description: String,
    val date: LocalDate,
    val time: LocalTime,
    val address: String,
    val tasks: List<Task>
)