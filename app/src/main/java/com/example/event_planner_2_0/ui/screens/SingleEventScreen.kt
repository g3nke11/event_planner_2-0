package com.example.event_planner_2_0.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.event_planner_2_0.ui.Navigation

@Composable
fun SingleEventScreen(host: NavHostController) {
    Column() {
        Text(text = "Single event screen")
        Spacer(Modifier.width(8.dp))
        NavButton(
            content = "Back To Events",
            route = Navigation.Events.route,
            host = host
        )
        Spacer(Modifier.width(8.dp))
        NavButton(
            content = "Create New Task",
            route = Navigation.NewTask.route,
            host = host
        )
    }
}