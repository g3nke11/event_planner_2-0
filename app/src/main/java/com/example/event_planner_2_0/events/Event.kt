package com.example.event_planner_2_0.events

import com.example.event_planner_2_0.entities.Task
import java.time.LocalDate
import java.time.LocalTime

data class Event(
    val id: String = "",
    val type: String = "",
    val title: String = "",
    val description: String = "",
    val date: com.google.firebase.Timestamp? = null,  // Use Firestore Timestamp
    val time: com.google.firebase.Timestamp? = null,  // Use Firestore Timestamp
    val address: String = "",
    val tasks: List<Task> = listOf()
)
