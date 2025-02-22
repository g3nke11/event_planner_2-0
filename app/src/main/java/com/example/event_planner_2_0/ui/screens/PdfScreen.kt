package com.example.event_planner_2_0.ui.screens

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
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

@Composable
fun PdfScreen(nav: NavHostController) {
    Text(text = "pdf screen")
    GeneratePDF(myEvent)
}

@Composable
private fun GeneratePDF(event: Event) {

    val context = LocalContext.current
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
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "sample.pdf")
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
