package com.example.event_planner_2_0.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.event_planner_2_0.entities.Task
import com.example.event_planner_2_0.events.Event
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.LocalTime

val myEvent: Event = Event(
    type = "Event",
    title = "Birthday Party",
    description = "This is my plan for my Birthday Party",
    date = LocalDate.now(),
    time = LocalTime.now(),
    address = "570 South 2nd West, Rexburg ID",
    tasks = listOf(
        Task(
            description = "Buy a cake",
            startTime = LocalTime.now(),
            endTime = LocalTime.now()
        ),
        Task(
            description = "Hire a jester",
            startTime = LocalTime.now(),
            endTime = LocalTime.now()
        ),
        Task(
            description = "Buy presents",
            startTime = LocalTime.now(),
            endTime = LocalTime.now()
        )
    )
)

@Composable
fun EventsScreen() {
    Text(text = "Events Screen")
    ListItem(myEvent)
    ListItem(myEvent)
    ListItem(myEvent)
}

@Composable
fun ListItem(event: Event) {
    Card(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = event.title
            )
            Text(
                text = event.description
            )
        }
    }
}