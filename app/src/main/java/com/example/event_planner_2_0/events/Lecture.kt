package com.example.event_planner_2_0.events

import com.example.event_planner_2_0.events.Event
import com.example.event_planner_2_0.entities.Task
import java.time.LocalDate
import java.time.LocalTime

class Lecture(
    val speaker: String,
    val topic: String,
    override val title: String,
    override val description: String,
    override val date: LocalDate,
    override val time: LocalTime,
    override val address: String,
    override val tasks: List<Task>
) : Event(title, description, date, time, address, tasks) {
}