package com.example.event_planner_2_0.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.event_planner_2_0.events.Event
import com.example.event_planner_2_0.ui.Navigation
import androidx.navigation.NavHostController
import com.example.event_planner_2_0.entities.Task
import java.time.LocalDate
import java.time.LocalTime
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


val myEvent: Event = Event(
    type = "Event",
    title = "Birthday Party",
    description = "This is my plan for my Birthday Party",
    date = LocalDate.now(),
    time = LocalTime.now(),
    address = "570 South 2nd West, Rexburg ID",
    tasks = listOf(
        Task(name = "Buy a cake", description = "Get a big chocolate cake"),
        Task(name = "Hire a jester", description = "Find a funny jester for entertainment"),
        Task(name = "Buy presents", description = "Get presents for friends and family")
    )
)

@Composable
fun EventsScreen(host: NavHostController) {
    val db = FirebaseFirestore.getInstance()

    // State to store the list of events
    val events = remember { mutableStateOf<List<Event>>(emptyList()) }

    // Fetch data from Firestore when the composable is first loaded
    LaunchedEffect(Unit) {
        // Firestore query to fetch events
        db.collection("events")
            .get()
            .addOnSuccessListener { result: QuerySnapshot ->
                // Map the query results to Event objects and update the state
                val eventList = result.documents.mapNotNull { document ->
                    try {
                        val event = document.toObject(Event::class.java)
                        event?.copy(id = document.id) // Add Firestore document ID
                    } catch (e: Exception) {
                        Log.e("Firestore", "Error parsing event", e)
                        null
                    }
                }
                events.value = eventList // Update the state with the fetched events
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting events: $exception")
            }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(events.value) { event ->
                EventCard(event)
            }
        }

        // Spacer between the list and the buttons
        Spacer(Modifier.height(16.dp))

        // Buttons
        EventNavButton(content = "New Event", route = Navigation.NewEvent.route, host = host)
        Spacer(Modifier.height(8.dp))
        EventNavButton(content = "Back to Home", route = Navigation.Home.route, host = host)
    }
}

@Composable
fun EventCard(event: Event) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Display event title and description
            Text(text = event.title)
            Text(text = event.description)
            Spacer(Modifier.height(8.dp))

            // Display tasks associated with the event
            Text(text = "Tasks:")
            event.tasks.forEach { task ->
                TaskItem(task)
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        // Display task information
        Text(text = "Task: ${task.name}")
        Text(text = "Description: ${task.description}")
        Text(text = "Completed: ${if (task.completed) "Yes" else "No"}")
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun EventNavButton(content: String, route: String, host: NavHostController) {
    Button(onClick = { host.navigate(route) }) {
        Text(content)
    }
}
