package com.example.event_planner_2_0

import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.event_planner_2_0.ui.EventPlanner
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this) // Initialize Firebase

        setContent{
            EventPlanner()
        }
    }
}


