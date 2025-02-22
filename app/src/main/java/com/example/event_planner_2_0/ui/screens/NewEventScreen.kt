package com.example.event_planner_2_0.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Column
import com.example.event_planner_2_0.events.Event
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun NewEventScreen() {
    Text(text = "New event screen")
}

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