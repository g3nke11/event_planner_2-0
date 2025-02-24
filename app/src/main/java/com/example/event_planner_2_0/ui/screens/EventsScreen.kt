package com.example.event_planner_2_0.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.event_planner_2_0.entities.Task
import com.example.event_planner_2_0.events.Event
import java.time.LocalDate
import java.time.LocalTime
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.navigation.NavHostController
import com.example.event_planner_2_0.ui.Navigation


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
fun EventsScreen(host: NavHostController) {

    val events = listOf<Event>(
        myEvent,
        myEvent,
        myEvent
    )
    Column() {
        LazyColumn {
            items(3) {
                Text(text = "Event")
                ListItem(myEvent)
            }
        }
        Spacer(Modifier.width(8.dp))
        NavButton(
            content = "Go to Home",
            route = Navigation.Home.route,
            host = host
        )
        Spacer(Modifier.width(8.dp))
        NavButton(
            content = "New Event",
            route = Navigation.NewEvent.route,
            host = host
        )

    }
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