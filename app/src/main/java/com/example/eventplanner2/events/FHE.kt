package com.example.eventplanner2.events

import com.example.eventplanner2.events.Event
import com.example.eventplanner2.entities.Task
import java.time.LocalDate
import java.time.LocalTime

class FHE(
    override  val type: String = "FHE",
    override val title: String,
    override val description: String,
    override val date: LocalDate,
    override val time: LocalTime,
    override val address: String,
    override val tasks: List<Task>
) : Event(type, title, description, date, time, address, tasks) {
}