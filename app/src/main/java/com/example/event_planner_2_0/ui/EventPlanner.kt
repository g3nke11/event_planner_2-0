package com.example.event_planner_2_0.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.event_planner_2_0.ui.screens.EventsScreen
import com.example.event_planner_2_0.ui.screens.HomeScreen
import com.example.event_planner_2_0.ui.screens.NewEventScreen
import com.example.event_planner_2_0.ui.screens.NewTaskScreen
import com.example.event_planner_2_0.ui.screens.NewTemplateScreen
import com.example.event_planner_2_0.ui.screens.PdfScreen
import com.example.event_planner_2_0.ui.screens.SingleEventScreen
import com.example.event_planner_2_0.ui.theme.EventPlanner20Theme

@Composable
fun EventPlanner() {
    val navController = rememberNavController()

    EventPlanner20Theme {
        NavHost(
            navController = navController,
            startDestination = Navigation.Home.route,
            modifier = Modifier
                .padding(vertical = 70.dp)
                .padding(horizontal = 10.dp)
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