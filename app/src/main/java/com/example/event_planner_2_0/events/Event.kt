package com.example.event_planner_2_0.events

import com.example.event_planner_2_0.entities.Task
import java.time.LocalDate
import java.time.LocalTime

open class Event(
    open val title: String,
    open val description: String,
    open val date: LocalDate,
    open val time: LocalTime,
    open val address: String,
    open val tasks: List<Task>
    ) {
}