package com.example.event_planner_2_0.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.event_planner_2_0.ui.Navigation

@Composable
fun HomeScreen(host: NavHostController) {
    Column() {
        Text(text = "home screen")
        Spacer(Modifier.width(8.dp))
        NavButton(
            content = "Go to Events",
            route = Navigation.Events.route,
            host = host
        )
    }
}

@Composable
fun NavButton(content: String, route: String, host: NavHostController) {
    Button(onClick = {
        host.navigate(route)
    }) {
        Text(content)
    }
}
