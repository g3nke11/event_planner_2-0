package com.example.event_planner_2_0.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.event_planner_2_0.ui.Navigation
import androidx.compose.foundation.layout.*
import androidx.navigation.NavController
import android.content.Context

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Button to navigate to EventsScreen
        Button(onClick = { navController.navigate(Navigation.Events.route) }) {
            Text(text = "View Events")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to navigate to NewEventScreen
        Button(onClick = { navController.navigate(Navigation.NewEvent.route) }) {
            Text(text = "Create New Event")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to generate PDF
        Button(onClick = { GeneratePDF(myEvent, navController.context) }) {
            Text(text = "Generate PDF")
        }

        Spacer(modifier = Modifier.height(16.dp))
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

