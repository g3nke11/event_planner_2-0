package com.example.event_planner_2_0

import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.event_planner_2_0.ui.EventPlanner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            EventPlanner()
        }
    }
}


