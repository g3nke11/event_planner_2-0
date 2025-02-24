package com.example.event_planner_2_0

import com.example.event_planner_2_0.events.Event
import com.example.event_planner_2_0.events.FHE
import com.example.event_planner_2_0.events.Lecture
import com.example.event_planner_2_0.events.Wedding

import android.graphics.pdf.PdfDocument
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.event_planner_2_0.databinding.ActivityMainBinding
import android.os.Environment
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
//import java.lang.reflect.Modifier
import android.widget.Button as Button
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val events = listOf<Event>()
        setContent {
            RSSReaderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    LazyColumn {
                        items(events) {
                            ListItem(it)
                        }
                    }
                }
            }

        }

        val btnGeneratePDF: Button = findViewById(R.id.btn_generate_pdf)
        btnGeneratePDF.setOnClickListener {
            generatePDF()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun generatePDF() {
        val pdfDocument = PdfDocument()
        val paint = Paint()
        val pageInfo = PdfDocument.PageInfo.Builder(300, 600, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = page.canvas

        // Draw text
        paint.textSize = 16f
        paint.isFakeBoldText = true
        canvas.drawText("Hello, this is a sample PDF!", 50f, 50f, paint)

        pdfDocument.finishPage(page)

        // Save the PDF file
        val file = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "sample.pdf")
        try {
            val fos = FileOutputStream(file)
            pdfDocument.writeTo(fos)
            pdfDocument.close()
            fos.close()
            println("PDF Created: ${file.absolutePath}")
        } catch (e: IOException) {
            e.printStackTrace()
        }
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
                "Title: ${event.title}"
            )
            Text(
                "Description: ${event.description}"
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventForm(eventType: String, onSubmit: (Event) -> Unit) {
    val context = LocalContext.current

    // State variables for form fields
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var date by remember { mutableStateOf(LocalDate.now()) }
    var time by remember { mutableStateOf(LocalTime.now()) }
    var address by remember { mutableStateOf(TextFieldValue("")) }

    // Date picker dialog
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            date = LocalDate.of(year, month + 1, dayOfMonth) // Month is 0-based
        },
        date.year, date.monthValue - 1, date.dayOfMonth
    )

    // Time picker dialog
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            time = LocalTime.of(hourOfDay, minute)
        },
        time.hour, time.minute, true // 24-hour format
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Create Event ($eventType)", style = MaterialTheme.typography.headlineMedium)

        // Title input
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        // Description input
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        // Date picker button
        Button(onClick = { datePickerDialog.show() }) {
            Text("Select Date: ${date.toString()}")
        }

        // Time picker button
        Button(onClick = { timePickerDialog.show() }) {
            Text("Select Time: ${time.toString()}")
        }

        // Address input
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )

        // Submit button
        Button(
            onClick = {
                // Create an Event object and pass it to onSubmit
                val event = Event(
                    title = title.text,
                    description = description.text,
                    date = date,
                    time = time,
                    address = address.text,
                    tasks = emptyList() // Tasks will be added later
                )
                onSubmit(event)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Event")
        }
    }
}