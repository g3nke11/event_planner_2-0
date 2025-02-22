package com.example.event_planner_2_0.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.Navigation

@Composable
fun SingleEventScreen(nav: NavHostController) {
    Text(text = "single event screen")
    nav.navigate(EventsScreen(nav))
}