package com.example.event_planner_2_0.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.event_planner_2_0.events.Event
import com.example.event_planner_2_0.entities.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.event_planner_2_0.ui.Navigation
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import com.google.firebase.Timestamp
import java.util.*

val myEvent: Event = Event(
    type = "Event",
    title = "Birthday Party",
    description = "This is my plan for my Birthday Party",
    // Convert LocalDate to Firestore Timestamp
    date = Timestamp(Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant())),
    // Convert LocalTime to Firestore Timestamp
    time = Timestamp(Date.from(LocalTime.now().atDate(LocalDate.now()).atZone(java.time.ZoneId.systemDefault()).toInstant())),
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

    // Firestore query to fetch events
    LaunchedEffect(Unit) {
        db.collection("events")
            .get()
            .addOnSuccessListener { result: QuerySnapshot ->
                // Map the query results to Event objects and update the state
                val eventList = result.documents.mapNotNull { document ->
                    try {
                        // Handle 'date' and 'time' fields as Timestamps
                        val firestoreDate = document.getTimestamp("date")?.toDate()
                        val firestoreTime = document.getTimestamp("time")?.toDate()

                        // Create Event object with the raw Timestamps
                        val event = document.toObject(Event::class.java)
                        event?.copy(
                            id = document.id,
                            date = firestoreDate?.let { Timestamp(it) },
                            time = firestoreTime?.let { Timestamp(it) }
                        )
                    } catch (e: Exception) {
                        Log.e("Firestore", "Error parsing event", e)
                        null
                    }
                }

                Log.d("Firestore", "Fetched Events: $eventList")
                events.value = eventList // Update the state with the fetched events
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting events: $exception")
            }
    }

    // Layout for displaying events
    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(events.value, key = { it.id }) { event ->
                EventCard(event, db, events)
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
fun EventCard(event: Event, db: FirebaseFirestore, events: MutableState<List<Event>>) {
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

            Spacer(modifier = Modifier.height(16.dp))

            // Delete Button
            Button(
                onClick = {
                    deleteEvent(event.id, db, events)
                }
            ) {
                Text(text = "Delete Event")
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

fun deleteEvent(eventId: String, db: FirebaseFirestore, events: MutableState<List<Event>>) {
    db.collection("events")
        .document(eventId)
        .delete()
        .addOnSuccessListener {
            Log.d("Firestore", "Event deleted successfully.")
            // Update the events list to remove the deleted event
            events.value = events.value.filter { it.id != eventId }
        }
        .addOnFailureListener { exception ->
            Log.e("Firestore", "Error deleting event: $exception")
        }
}
