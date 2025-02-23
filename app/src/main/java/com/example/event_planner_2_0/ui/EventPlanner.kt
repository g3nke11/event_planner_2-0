package com.example.event_planner_2_0.ui

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
                HomeScreen(navController)
            }
            composable(route = Navigation.Events.route) {
                EventsScreen(navController)
            }
            composable(route = Navigation.SingleEvent.route) {
                SingleEventScreen(navController)
            }
            composable(route = Navigation.Pdf.route) {
                PdfScreen(navController)
            }
            composable(route = Navigation.NewTemplate.route) {
                NewTemplateScreen(navController)
            }
            composable(route = Navigation.NewEvent.route) {
                NewEventScreen(navController)
            }
            composable(route = Navigation.NewTask.route) {
                NewTaskScreen(navController)
            }
        }
    }
}