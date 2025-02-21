package com.example.event_planner_2_0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.event_planner_2_0.ui.Navigation
import com.example.event_planner_2_0.ui.screens.EventsScreen
import com.example.event_planner_2_0.ui.screens.HomeScreen
import com.example.event_planner_2_0.ui.screens.NewEventScreen
import com.example.event_planner_2_0.ui.screens.NewTaskScreen
import com.example.event_planner_2_0.ui.screens.NewTemplateScreen
import com.example.event_planner_2_0.ui.screens.PdfScreen
import com.example.event_planner_2_0.ui.screens.SingleEventScreen
import com.example.event_planner_2_0.ui.theme.EventPlanner20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            EventPlanner20Theme {
                NavHost(
                    navController = navController,
                    startDestination = Navigation.Pdf.route,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(route = Navigation.Home.route) {
                        HomeScreen()
                    }
                    composable(route = Navigation.Events.route) {
                        EventsScreen()
                    }
                    composable(route = Navigation.SingleEvent.route) {
                        SingleEventScreen()
                    }
                    composable(route = Navigation.Pdf.route) {
                        PdfScreen()
                    }
                    composable(route = Navigation.NewTemplate.route) {
                        NewTemplateScreen()
                    }
                    composable(route = Navigation.NewEvent.route) {
                        NewEventScreen()
                    }
                    composable(route = Navigation.NewTask.route) {
                        NewTaskScreen()
                    }
                }
            }
        }
    }
}