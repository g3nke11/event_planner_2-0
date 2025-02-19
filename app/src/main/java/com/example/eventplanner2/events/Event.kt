package com.example.eventplanner2.events

import com.example.eventplanner2.entities.Task
import java.time.LocalDate
import java.time.LocalTime

open class Event(
    open val type: String = "Event",
    open val title: String,
    open val description: String,
    open val date: LocalDate,
    open val time: LocalTime,
    open val address: String,
    open val tasks: List<Task>
    ) {
}