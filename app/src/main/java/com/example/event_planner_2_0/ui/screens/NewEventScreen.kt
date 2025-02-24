package com.example.event_planner_2_0.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Column
import com.example.event_planner_2_0.events.Event
import com.example.event_planner_2_0.entities.Task
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.unit.dp
import com.example.event_planner_2_0.ui.Navigation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import android.widget.Toast
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NewEventScreen(navController: NavHostController) {
    // Variables to store user input
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(LocalDate.now()) }
    var time by remember { mutableStateOf(LocalTime.now()) }
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var tasks by remember { mutableStateOf(listOf<Task>()) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // DatePicker dialog
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            date = LocalDate.of(year, month + 1, dayOfMonth) // month is 0-based in DatePickerDialog
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // TimePicker dialog
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            time = LocalTime.of(hourOfDay, minute)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true // 24-hour format
    )


    // Function to save the event
    fun saveEvent() {
        // Convert LocalDate to java.util.Date
        val dateInMillis = Date.from(date.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant())
        val timestampDate = com.google.firebase.Timestamp(dateInMillis)

        // Convert LocalTime to java.util.Date (just the time part with today's date)
        val timeInMillis = Date.from(time.atDate(LocalDate.now()).atZone(java.time.ZoneId.systemDefault()).toInstant())
        val timestampTime = com.google.firebase.Timestamp(timeInMillis)

        val event = Event(
            id = "",
            type = "Event",
            title = title,
            description = description,
            date = timestampDate,  // Firestore Timestamp
            time = timestampTime,  // Firestore Timestamp
            address = address,
            tasks = tasks
        )

        val db = FirebaseFirestore.getInstance()
        db.collection("events")
            .add(event) // Firestore will automatically assign a document ID
            .addOnSuccessListener { documentReference ->
                Toast.makeText(
                    context,
                    "Event added with ID: ${documentReference.id}",
                    Toast.LENGTH_SHORT
                ).show()
                // Navigate to the events screen after saving
                navController.navigate(Navigation.Events.route)
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    context,
                    "Error adding event: $e",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    // UI for New Event Screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Create New Event", modifier = Modifier.padding(bottom = 16.dp))

        // Event Title
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Event Title") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Event Description
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Event Description") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Event Address
        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Event Address") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Event Date
        Text("Event Date: $date")
        Button(onClick = { datePickerDialog.show() }) {
            Text("Pick Date")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Event Time
        Text("Event Time: $time")
        Button(onClick = { timePickerDialog.show() }) {
            Text("Pick Time")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Task name and description input
        TextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("Task Name") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = taskDescription,
            onValueChange = { taskDescription = it },
            label = { Text("Task Description") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Add Task Button
        Button(onClick = {
            if (taskName.isNotEmpty() && taskDescription.isNotEmpty()) {
                tasks = tasks + Task(name = taskName, description = taskDescription)
                taskName = ""
                taskDescription = ""
            }
        }) {
            Text(text = "Add Task")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // List Tasks
        tasks.forEach { task ->
            Text(text = "Task: ${task.name} - ${task.description}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Save Button
        Button(onClick = { saveEvent() }) {
            Text(text = "Save Event")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Navigation Button (Back to Home)
        Button(onClick = { navController.navigate(Navigation.Home.route) }) {
            Text(text = "Back to Home")
        }
    }
}




//@Composable
//fun NewEventScreen(host: NavHostController) {
//
//    Column() {
//        Text(text = "New event screen")
//        Spacer(Modifier.width(8.dp))
//        NavButton(
//            content = "Cancel",
//            route = Navigation.Events.route,
//            host = host
//        )
//        Spacer(Modifier.width(8.dp))
//        NavButton(
//            content = "Go to New Single Event",
//            route = Navigation.SingleEvent.route,
//            host = host
//        )
//    }
//}
//
//@Composable
//fun NewEventDialog(
//    host: NavHostController,
//    onDismiss: () -> Unit,  // to dismiss the dialog
//    onEventCreated: (Event) -> Unit  // callback to handle event creation
//) {
//    var eventTitle by remember { mutableStateOf(TextFieldValue()) }
//    var eventDescription by remember { mutableStateOf(TextFieldValue()) }
//    var eventAddress by remember { mutableStateOf(TextFieldValue()) }
//    var eventDate by remember { mutableStateOf(LocalDate.now()) }
//    var eventTime by remember { mutableStateOf(LocalTime.now()) }
//    var tasks by remember { mutableStateOf(listOf<Task>()) }
//    var taskInputName by remember { mutableStateOf(TextFieldValue()) }
//    var taskInputDescription by remember { mutableStateOf(TextFieldValue()) }
//
//    // Date Picker
//    val context = LocalContext.current
//    val datePickerDialog = remember {
//        DatePickerDialog(
//            context,
//            { _, year, month, dayOfMonth ->
//                eventDate = LocalDate.of(year, month + 1, dayOfMonth)
//            },
//            eventDate.year,
//            eventDate.monthValue - 1,
//            eventDate.dayOfMonth
//        )
//    }
//
//    // Time Picker
//    val timePickerDialog = remember {
//        TimePickerDialog(
//            context,
//            { _, hourOfDay, minute ->
//                eventTime = LocalTime.of(hourOfDay, minute)
//            },
//            eventTime.hour,
//            eventTime.minute,
//            true
//        )
//    }
//
//    // AlertDialog to create a new event
//    AlertDialog(
//        onDismissRequest = { onDismiss() },
//        title = { Text(text = "Create New Event") },
//        text = {
//            Column(modifier = Modifier.padding(16.dp)) {
//                OutlinedTextField(
//                    value = eventTitle,
//                    onValueChange = { eventTitle = it },
//                    label = { Text("Title") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//
//                OutlinedTextField(
//                    value = eventDescription,
//                    onValueChange = { eventDescription = it },
//                    label = { Text("Description") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//
//                OutlinedTextField(
//                    value = eventAddress,
//                    onValueChange = { eventAddress = it },
//                    label = { Text("Address") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//                    Button(onClick = { datePickerDialog.show() }) {
//                        Text("Pick Date")
//                    }
//                    Text(text = eventDate.toString()) // Show selected date
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//                    Button(onClick = { timePickerDialog.show() }) {
//                        Text("Pick Time")
//                    }
//                    Text(text = eventTime.toString()) // Show selected time
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // Add task name and description inputs
//                OutlinedTextField(
//                    value = taskInputName,
//                    onValueChange = { taskInputName = it },
//                    label = { Text("Task Name") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//
//                OutlinedTextField(
//                    value = taskInputDescription,
//                    onValueChange = { taskInputDescription = it },
//                    label = { Text("Task Description") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Button(
//                    onClick = {
//                        if (taskInputName.text.isNotEmpty() && taskInputDescription.text.isNotEmpty()) {
//                            val newTask = Task(
//                                name = taskInputName.text,
//                                description = taskInputDescription.text
//                            )
//                            tasks = tasks + newTask
//                            taskInputName = TextFieldValue()
//                            taskInputDescription = TextFieldValue()
//                        }
//                    },
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text("Add Task")
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // Display the tasks
//                LazyColumn(modifier = Modifier.fillMaxWidth()) {
//                    items(tasks) { task ->
//                        Text(text = "${task.name}: ${task.description}", modifier = Modifier.padding(4.dp))
//                    }
//                }
//            }
//        },
//        confirmButton = {
//            Button(
//                onClick = {
//                    // Handle event creation
//                    onEventCreated(Event(
//                        title = eventTitle.text,
//                        description = eventDescription.text,
//                        date = eventDate,
//                        time = eventTime,
//                        address = eventAddress.text,
//                        tasks = tasks
//                    ))
//                    onDismiss()
//                }
//            ) {
//                Text("Create Event")
//            }
//        },
//        dismissButton = {
//            Button(onClick = { onDismiss() }) {
//                Text("Cancel")
//            }
//        }
//    )
//}
//
//@Preview
//@Composable
//fun NewEventDialogPreview() {
//    var dialogVisible by remember { mutableStateOf(true) }
//
//    if (dialogVisible) {
//        NewEventDialog(
//            host = NavHostController(context = LocalContext.current),
//            onDismiss = { dialogVisible = false },
//            onEventCreated = { event ->
//                // Handle the created event here
//            }
//        )
//    }
//}

//
// @Composable
// fun EventForm(eventType: String, onSubmit: (Event) -> Unit) {
//     val context = LocalContext.current
//
//     // State variables for form fields
//     var title by remember { mutableStateOf(TextFieldValue("")) }
//     var description by remember { mutableStateOf(TextFieldValue("")) }
//     var date by remember { mutableStateOf(LocalDate.now()) }
//     var time by remember { mutableStateOf(LocalTime.now()) }
//     var address by remember { mutableStateOf(TextFieldValue("")) }
//
//     // Date picker dialog
//     val datePickerDialog = DatePickerDialog(
//         context,
//         { _, year, month, dayOfMonth ->
//             date = LocalDate.of(year, month + 1, dayOfMonth) // Month is 0-based
//         },
//         date.year, date.monthValue - 1, date.dayOfMonth
//     )
//
//     // Time picker dialog
//     val timePickerDialog = TimePickerDialog(
//         context,
//         { _, hourOfDay, minute ->
//             time = LocalTime.of(hourOfDay, minute)
//         },
//         time.hour, time.minute, true // 24-hour format
//     )
//
//     Column(
//         modifier = Modifier
//             .fillMaxSize()
//             .padding(16.dp),
//         verticalArrangement = Arrangement.spacedBy(16.dp)
//     )
//     {
//         Text("Create Event ($eventType)", style = MaterialTheme.typography.headlineMedium)
//
//         // Title input
//         OutlinedTextField(
//             value = title,
//             onValueChange = { title = it },
//             label = { Text("Title") },
//             modifier = Modifier.fillMaxWidth()
//         )
//
//         // Description input
//         OutlinedTextField(
//             value = description,
//             onValueChange = { description = it },
//             label = { Text("Description") },
//             modifier = Modifier.fillMaxWidth()
//         )
//
//         // Date picker button
//         Button(onClick = { datePickerDialog.show() }) {
//             Text("Select Date: ${date.toString()}")
//         }
//
//         // Time picker button
//         Button(onClick = { timePickerDialog.show() }) {
//             Text("Select Time: ${time.toString()}")
//         }
//
//         // Address input
//         OutlinedTextField(
//             value = address,
//             onValueChange = { address = it },
//             label = { Text("Address") },
//             modifier = Modifier.fillMaxWidth()
//         )
//
//         // Submit button
//         Button(
//             onClick = {
//                 // Create an Event object and pass it to onSubmit
//                 val event = Event(
//                     title = title.text,
//                     description = description.text,
//                     date = date,
//                     time = time,
//                     address = address.text,
//                     tasks = emptyList() // Tasks will be added later
//                 )
//                 onSubmit(event)
//             },
//             modifier = Modifier.fillMaxWidth()
//         ) {
//             Text("Submit Event")
//         }
//     }
// }