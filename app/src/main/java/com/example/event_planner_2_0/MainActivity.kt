package com.example.event_planner_2_0

import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import com.example.event_planner_2_0.databinding.ActivityMainBinding
import android.os.Environment
import android.widget.Button
import android.graphics.Canvas
import android.graphics.Paint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val btnAddEvent: Button = findViewById(R.id.AddEvent)

        btnAddEvent.setOnClickListener {
            showAddEventDialog()
        }

        val btnGeneratePDF: Button = findViewById(R.id.btn_generate_pdf)
        btnGeneratePDF.setOnClickListener {
            generatePDF()
        }
    }

    private fun showAddEventDialog() {
        // Inflate the custom layout
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_event, null)
        val editTitle: EditText = dialogView.findViewById(R.id.editTitle)
        val editDescription: EditText = dialogView.findViewById(R.id.editDescription)

        // Create the dialog
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Add Event")
            .setPositiveButton("Save") { _, _ ->
                val title = editTitle.text.toString()
                val description = editDescription.text.toString()
                saveEvent(title, description)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun saveEvent(title: String, description: String) {
        if (title.isNotEmpty() && description.isNotEmpty()) {
            Toast.makeText(this, "Event Saved: $title", Toast.LENGTH_SHORT).show()
            // TODO: Store the event (e.g., in a database or list)
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
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