package com.example.event_planner_2_0.ui.screens

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.view.View
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.event_planner_2_0.entities.Task
import com.example.event_planner_2_0.events.Event
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDate
import java.time.LocalTime
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.ComponentActivity

@Composable
fun PdfScreen(host: NavHostController) {
    var message by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            message = GeneratePDF(myEvent, context).toString()
        }) {
            Text("Generate PDF")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
    }
}

fun GeneratePDF(event: Event, context: Context) {
     val pdfDocument = PdfDocument()
     val paint = Paint()
     val pageInfo = PdfDocument.PageInfo.Builder(300, 600, 1).create()
     val page = pdfDocument.startPage(pageInfo)
     val canvas: Canvas = page.canvas

     // Draw text
     paint.textSize = 16f
     paint.isFakeBoldText = true

     when (event.type) {
         "Event" -> {
             canvas.drawText("~${event.title}~", 50f, 50f, paint)
             canvas.drawText("${event.date}", 50f, 50f, paint)
             canvas.drawText("${event.time}", 50f, 50f, paint)
             canvas.drawText("Location: ${event.address}", 50f, 50f, paint)
             canvas.drawText("What will happen? ${event.description}", 50f, 50f, paint)
         }
         "FHE" -> {
             canvas.drawText("~${event.title}~", 50f, 50f, paint)
             canvas.drawText("${event.date}", 50f, 50f, paint)
             canvas.drawText("${event.time}", 50f, 50f, paint)
             canvas.drawText("Location: ${event.address}", 50f, 50f, paint)
             canvas.drawText("What will happen? ${event.description}", 50f, 50f, paint)
         }
         "Lecture" -> {
             canvas.drawText("~${event.title}~", 50f, 50f, paint)
             canvas.drawText("${event.date}", 50f, 50f, paint)
             canvas.drawText("${event.time}", 50f, 50f, paint)
             canvas.drawText("Location: ${event.address}", 50f, 50f, paint)
             canvas.drawText("What will happen? ${event.description}", 50f, 50f, paint)
         }
         "Wedding" -> {
             canvas.drawText("~${event.title}~", 50f, 50f, paint)
             canvas.drawText("${event.date}", 50f, 50f, paint)
             canvas.drawText("${event.time}", 50f, 50f, paint)
             canvas.drawText("Location: ${event.address}", 50f, 50f, paint)
             canvas.drawText("What will happen? ${event.description}", 50f, 50f, paint)
         }
     }


     pdfDocument.finishPage(page)


     // Save the PDF file
     val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "event.pdf")
     try {
         val fos = FileOutputStream(file)
         pdfDocument.writeTo(fos)
         pdfDocument.close()
         fos.close()
         println("PDF Created: ${file.absolutePath}")
     }
     catch (e: IOException) {
         e.printStackTrace()
     }

}